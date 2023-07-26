# Estándar de Backend

### Configuración de IDEs

La configuración a realizar es el estilo de google, se puede acceder a los archivos en el siguiente
repositorio **[Google style](https://github.com/google/styleguide)**

Se seleccionará el estilo de java para el editor o IDE que se utilize.

### Nombrado del proyecto

Cada repositorio o proyecto tiene un patrón de como deben ser nombrados. La estructa que se va a
emplear es la siguiente:

**\<name_1>-\<name_2>-\<name_3>**

* name_1 -> iniciales de tu celula (Crédito)
* name_2 -> microservice architecture
* name_3 -> nombre del proyecto

Por ejemplo: **crd-msa-customer**

### Estructura de paquetes

Para el manejo de la estructura de de los paquetes se tomará como
referencia [Domain driven design](https://es.wikipedia.org/wiki/Dise%C3%B1o_guiado_por_el_dominio),
en donde tendremos de ejemplo la siguiente estructura:

#### Proyectos REST Ejemplo

~~~
* helm
  * development.yaml 
  * production.yaml
  * staging.yaml
* src
  * main
    * java
      * com.pichincha.<celula>.<nombre-proyecto>
        * configuration
        * respository
          *impl
        * service
          *impl
        * controller
        * exception
        * domain
          * dto
          * enums
        * util
        * helper
    * resources
      - application.yml
      * db.migration
  * test
    * java
      * com.pichincha.test.<celula>.<nombre-proyecto>
        * configuration
        * respository
        * service
        * controller
        * util
        * helper
- azure-pipelines.yml
- .gitignore
- README.md
- pom.xml
- nombre-proyecto.yml (documentación API)
- settings.xml
- Dockerfile


~~~

#### Proyectos WebFlux Ejemplo

~~~
* helm
  * development.yaml
  * production.yaml
  * staging.yaml
* src
  * main
    * java
      * com.pichincha.<celula>.<nombre-proyecto>
        * configuration
        * respository
          *impl
        * handler
          *impl
        * service
          *impl     
        * exception     
        * domain
          * enums
          * dto
        * util
        * helper
    * resources
      - application.yml
      * db.migration
  * test
    * java
      * com.pichincha.test.<celula>.<nombre-proyecto>
        * configuration
        * respository
        * service
        * util
        * handler
        * helper
- azure-pipelines.yml
- .gitignore
- README.md
- pom.xml
- nombre-proyecto.yml (documentación API) 
- settings.xml
- Dockerfile
~~~

##### Config

En este paquete se incluyen todas las clases que hagan referencia a configuraciones del proyecto,
como por ejemplo:

* _ApplicationProperties.java_ (Mapear las variables establecidas en los resources
  @ConfigurationProperties)
* _DatabaseConfiguration.java_ (Configuración de la base de datos que se este utilizando)
* _CacheDBConfiguration.java_ (Configuración de la base de datos que se esté utilizando en el
  proyecto)
* _JacksonConfiguration.java_ (Configuración de la serialización de los objetos del proyecto)
* _LogginConfiguration.java_ (Configuración de los logs del proyecto) * Opcional
* _SecurityConfiguration.java_ (Configuración de la seguridad manejada en el proyecto, como las
  rutas permitidas y los roles permidos en caso de existir)
* _WebfluxConfiguration.java_ (En caso de ser el proyecto de tipo webflux, en esta clase se coloca
  las diferentes configuraciones establecidas para el proyecto)

##### Repository

Este paquete sirve para agregar las diferentes interfaces e implementaciones para la obtención de
datos de una DB u realizar peticiones a otros microservicios. Se debe tener una interface Repository
y una implementación de la misma. Ejemplo:

* repository

* ~~~java
  public interface CustomerRepository {
    Mono<ContractCustomerResponseDto> find(String identification, String identificationType);
  }
  ~~~

    * impl

        * ~~~java
      @Component
      @RequiredArgsConstructor
      public class CustomerRepositoryImpl implements CustomerRepository {
      
        private final WebClientHelper webClientHelper;
        private final TransactionTrackingHelper transactionTrackingHelper;
        
        @Value("${url.get.customer.contract-data}")
        private String urlGetCustomerContractData;
      
        public Mono<ContractCustomerResponseDto> find(String identification, String identificationType){
          return webClientHelper.builder()
              .header(transactionTrackingHelper.getMdcHeaders()).build().get()
              .uri(urlGetCustomerContractData, identification, identificationType)
              .retrieve().bodyToMono(ContractCustomerResponseDto.class);
        }
      }
      ~~~

##### Service

En este paquete se realiza todas las interfaces e implementaciones de los servicios (lógica) de
nuestro proyecto. Se debe tener una interface Service y una implementación de la misma. Ejemplo:

* service

*  ~~~java
   public interface UrlService {
     Mono<HashResponseDto> create(OtpRequest request);
   }
   ~~~

* impl

* ~~~ java
    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class UrlServiceImpl implements UrlService {
      private final CreateOfferStoreService createOfferStoreService;
      @Override
      public Mono<HashResponseDto> create(OtpRequest request) {
        return Mono.zip(Mono.just(request).subscribeOn(Schedulers.parallel()),
            createOfferStoreService.find(request.getIdentification(), 		     request.getIdentificationType()).subscribeOn(Schedulers.parallel()))
            .flatMap(this::validateStatusOffer);
      }
    
      private Mono<HashResponseDto> validateStatusOffer(Tuple2<OtpRequest, CreateOfferModel> tuple) {
        updateAndSaveCreateOffer(tuple);
        return offerStoreService.delete(request.getIdentification(), request.getIdentificationType())
            .flatMap(deleteStatus -> {
              return Mono.just(HashResponseDto.builder()
                  .code(deleteStatus.getCode())
                  .message(deleteStatus.getMessage())
                  .build());
            });
      }
    ~~~

##### Controller

Paquete donde se guardan las diferentes clases que contienen los endpoints de los servicios de
nuestro proyecto. Ejemplo:

* controller

* ~~~ java
  @Slf4j
  @RequiredArgsConstructor
  @RestController
  @RequestMapping("/api/v2")
  public class CheckOfferController {
      
    private String commonHeaderCrdDeviceIdKey;
  
    @PostMapping(path = "/check/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseDto> checkOffer(@RequestBody PersonDto personDto,
            @RequestHeader Map<String, String> headers) {
      
      headers.entrySet().forEach(mapEntry -> MDC.put(mapEntry.getKey(), mapEntry.getValue()));
      MDC.put(commonHeaderCrdSesionIdKey, commonHeaderCrdSesionIdKey);
  
      if (BooleanUtils.isFalse(validateCaptcha(personDto.getIdentification(), headers.get(RECAPTCHA_TOKEN)))) {
        return ResponseEntity.ok(CommonResponseDto.builder()
                .code(StatusCode.INVALID_RECAPTCHA.getCode())
                .message(StatusCode.INVALID_RECAPTCHA.getMessage()).build());
      }
  ~~~

##### Handler

Paquete donde se guardan las diferentes clases que contienen los endpoints de los servicios de
nuestro proyecto en caso de usar WebFlux. Se debe tener una interface handler y una implementación
de la misma. Ejemplo:

* handler

* ~~~ java
  public interface CreateHandler {
     Mono<ServerResponse> create(ServerRequest serverRequest);
  }
  ~~~

* impl

* ~~~ java
  @Component
  @RequiredArgsConstructor
  public class CreateHandlerImpl implements CreateHandler {
  
     private final TransactionTrackingHelper transactionTrackingHelper;
     private final CreateOfferService createOfferService;
     
     @Override
     public Mono<ServerResponse> status(ServerRequest serverRequest) {
        transactionTrackingHelper.mdcPut(serverRequest.headers());
        return this.createOfferService.status(serverRequest.pathVariable("identification"), serverRequest.pathVariable("identificationType"))
         .flatMap(response -> ok().body(fromValue(response)));
     }
  }
  ~~~

##### Domains

En este paquete se guardará los modelos utilizados en el proyecto. Dentro de este paquete existe uno
adicional que es el de enum, paquete que sirve para guardar las diferentes clases tipo enum que
tenga el proyecto. Ejemplo:

~~~java

@Getter
public enum CustomerLogStatus {
  NO_INIT("NO_INICIADO"),
  INIT("INICIADO"),
  VALIDATED("VALIDADO"),
  NO_VALIDATED("NO_VALIDADO"),
  SIMULATED_OFFER("OFERTA_SIMULADA"),
  INIT_OPERATION("CREA_LA_OPERACION"),
  INTERNAL_ERROR("INTERNAL_ERROR"),
  FINALIZED("FINALIZADO"),
  CANCELED("CANCELADO");
}
~~~

Para la creación de los modelos se recomienda utilizar la dependencia Lombok, la misma que facilita
la implementación de código repetitivo, como los gets y sets de las variables, constructores, la
anotación Builder, etc.

##### Util

Paquete para las clases que tienen funciones utilizadas en algunas partes del proyecto. Clase de
transformación de datos. Clases estáticas.

* _Constants.java_ (Establecer las diferentes constantes que va a tener el proyecto)

##### Helper

Paquete para las clases que tienen funciones utilizadas en algunas partes del proyecto. Clases
instanciables

En este paquete también se puede agregar las clases TransactionTrackingUtil y WebClientUtil

* TransactionTrackingHelper (Componente para inyectar las cabeceras de las peticiones a los demas
  microservicios cuando sean invocados)
* WebFluxClientHelper | RestClientHelper (Componente que tiene la funcionalidad de invocar otros
  microservicios con la configuración establecida en esta clase).

##### Resources

Aquí se encuentran los archivos de las variables configuradas en el proyecto. Los archivos dependen
del framework y de arquitectura. Se recomienda el uso de yml y el uso de 4 archivos de variables. (*
Esto debe ser acordado con el Arquitecto y Devops del proyecto)

* application.yml (configuración y variables para ambiente local)
* application-dev.yml (configuración y variables para ambiente de desarrollo)
* application-staging.yml (configuración y variables para ambiente de pruebas)
* application-prod.yml (configuración y variables para ambiente de producción)

Adicional se encuentra un paquete denominado db.migration que tiene como objetivo almacenar los
diferentes scripts SQL que vaya modificando la base de datos, para el nombramiento de estos scripts,
van a seguir el siguiente patron:

Ejemplo:

* V\<anio>\<mes>\<dia>\<hora>\<minuto>__<create|update|delete>\_\<funcionalidad>.sql
    * V202101011201\_\_create_table_parameter.sql
* V\<Version>\<SubVersion>\<SubSubVersion>__<create|update|delete>\_\<funcionalidad>.sql
    * V1_0_0__create_table_parameter.sql (flyway)

### Nomenclatura

- Todo el código debe ser escrito en inglés, esto incluye a todo el proyecto (clases, paquetes,
  código)
- No se debe dejar código comentado
- Los comentarios agregados deben ser para documentar el código.
- En el caso del uso del lenguaje Java, se recomienda el uso de las librerías **lombok** y **
  mapstruct**
- Utilizar los lineamientos
  de [Clean code of  Robert C. Martin](https://www.amazon.com/-/es/Robert-C-Martin/dp/0132350882)

#### Clases

- El nombre de las clases deben ser a referencia de lo que va hacer la clase.
- Para su nombramiento se debe utilizar el estilo _CamelCase_ en el caso de **Java** y _snake_case_
  en el caso de **Python**.
- En el nombre no se debe incluir prefijos o sufijos como DAO, URL, HTML.
- Clases que sean implementación de una _interfaz_ se agregará a su nombre la terminación **Impl**

#### Métodos

- Los métodos deben ser verbos en infinitivo con la nomenclatura lowerCamelCase.
- Los métodos deben tener un nombre descriptivo que sean fáciles de LEER Y ENTENDER.
- Deben iniciar con un verbo que explique o permita intuir que función cumple dicho método.
- Con respecto al código el método debe ser pequeño, no debería sobrepasar los 20 –30 líneas como
  preferencia.
- Un método debe hacer UNA SOLA COSA, y ser nombrado por dicha función que realiza. Single
  Responsability.

#### Variables

- Los nombres de las variables deben ser relevantes y significativas a su uso: Ejemplo int
  elapsedTimeInDays.
- No poner nombres genéricos como por ejemplo accountList, sino algo más específico como
  customerBankAccountList.
- Evitar usar variables como i, j, k, l en los bucles. Esto cuando se tiene bucles anidados se
  vuelve muy complicado entender.
- Usar nombres de variables que se puedan buscar de forma rápida y sencilla. Para ello es necesario
  que cada variable tenga un nombre significativo en cada clase. Por ejemplo, si una variable se
  llama account, y se busca por medio del IDE, dicha variable puede aparecer en muchas clases más
  aparte de la deseada.
- La variable debe ser creada solo cuando se lo va a usar. (Cuidado con las variables en los return)

#### Constantes

- En caso de requerir utilizar constantes crear una clase Constant que contenga las constantes, los
  nombres de constantes de clases deberían escribirse todo en mayúsculas con las palabras separadas
  por subguión ("_"). Todas serán declaradas como public static final. Ejemplo:public static final
  String PROPERTY_URL_SERVICE = "urlServicio";

### Documentación

Para la documentación existen dos secciones, que se trata de:

- Documentación de código
- Documentación para APIs

#### Documentación de código

* Para documentar el código se utiliza el estandard de JAVADOC.
* No se permite realizar documentación dentro de los métodos o dejar código comentado en el
  proyecto.
* En el caso de Interfaces e implementaciones, se debe realizar la documentación en la interfaz y no
  en la implementación.
* Vincular la documentacion de la implementación con la interfaz @inheritDoc .

#### Documentación para APIs

* Para la documentación de las API en JAVA se utiliza el archivo swagger (yml) llamado
  nombre-proyecto.yml que se encuentra en la raíz del mismo.
* Es importante hacer la documentación previa a la programación como buenas prácticas.

~~~ yml
openapi: 3.0.0
info:
  version: 1.0.0
  title: Sample API
  description: A sample API to illustrate OpenAPI concepts
paths:
  /list:
    get:
      description: Returns a list of stuff              
      responses:
        '200':
          description: Successful response
~~~

### Comentarios

* La única razón para usar un comentario es por el hecho de brindar más información SIEMPRE Y CUANDO
  SEA NECESARIA.
* El uso de comentarios debe evitarse a como dé lugar.
* Siempre intente explicar su código sin el uso de comentarios.

Ejemplo:

* Incorrecto

~~~ java
// Check to see if the employee is eligible for full benefits  
if ((employee.flags & HOURLY_FLAG) && (employee.age > 65)) 
~~~

* Correcto

~~~ java
if (employee.isEligibleForFullBenefits())  
~~~

### Pruebas

* Agregar la palabra Test al final de la clase que se desea probar. EJemplo:
  SerializedPageResponderTest.
* En el nombre de cada prueba debe explicarse cuál va a ser el test a realizar y CUAL VA A SER EL
  RESULTADO esperado. Ejemplo: public void sendsIncorrectClientIdAndThrowsErrorTest()
* El mínimo de cobertura de pruebas debe ser 75%.

### Buenas prácticas

#### Standard dependecias

- Colocar los números de versión de las dependencias dentro de la sección de _
  \<applicationProperties\>_
- Incluir las versiones de todas las dependencias
- No incluir el número de versiones de las dependecias que forman parte de la lista de spring boot.
- No mezclar varias versiones de una dependecia en un proyecto, utilizar las funcionalidade de
  gestor de paquetes para ver el conflicto de versiones.

#### Favorezca la inyección de constructor en lugar de @Autowired

- Cuando se utiliza Autowired se puede crear dependecias circulares entre beans y por ende que la
  compilación falle.
- Facilita la creación de instancias.
- La mejor práctica para la inyección de dependecias en usar _Lombok_. Donde se debe declar una
  propiedad final del tipo interfaz y anotar la clase usando _@RequiredArgsConstructor_

#### Manejo de excepciones globales

- Spring boot proporciona dos formas principales de manejar excepciones a nivel global

    - HandlerExceptionResolver para definir la estrategia global de manejo de excepciones.

    - Anotar los controladores con la anotación _@ExceptionHandler_. Es útil para determinados
      casos, por ejemplo:

      ~~~java
      @ExceptionHandler(NotFoundException.class)
      @ResponseBody
      public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
          ErrorResponse errorResponse = ErrorResponse.builder()
                  .errorCode(HttpStatus.NOT_FOUND.toString())
                  .errorKey(UNKNOWN_DATA_ITEM.name())
                  .errorMessage(exception.getMessage()).build();
          return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
      }
      ~~~

### Estandard escritura

- Nunca utilizar System.Out o System.Err

### RECOMENDACIONES

* Utilizar maven wrapper **[Maven Wrapper](https://www.baeldung.com/maven-wrapper)**.
* Se recomienda el uso de patrones de diseño. Para obtener mayor información se puede usar el
  siguiente link **[Patrones de Diseño](https://refactoring.guru/es/design-patterns/catalog)**.
* Utilizar plugin de SonarLint para verificar el estilo del código y así evitar tener que mandar a
  pipeline para recibir feedfack.
* Exponer estados finales de los APIs utilizando actuator. (spring-boot-starter-actuator)
* Utilizar Beans de validación, para esto se puede generar anotaciones bean, para validaciones.
  Ejemplo:
  ~~~java
  @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
  @Retention(RUNTIME)
  @Repeatable(CurrencyCode.List.class)
  @Documented
  @Constraint(validatedBy = { CurrencyCodeValidator.class })
  public @interface CurrencyCode {
  
    String message() default "must be a valid currency code";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
  
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
      CurrencyCode[] value();
    }
  }
  ~~~
  Implementación
  ~~~java
  @CurrencyCode //custom annotation
  private String currency;
  ~~~

### Plantillas base

#### Spring rest

#### Spring webflux





