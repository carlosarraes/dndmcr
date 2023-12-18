FROM registry.access.redhat.com/ubi8/ubi-minimal:8.9
WORKDIR /work/

COPY target/*-runner /work/application
RUN chmod 775 /work/application

EXPOSE 8081
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
