ARG IMAGE
FROM $IMAGE

RUN mkdir /opt/build/
WORKDIR /opt/build/
COPY ./build/*.txt /opt/build/
COPY ./build/libs/*.jar /opt/build/

CMD ["/bin/sh", "/opt/build/init.sh"]
