FROM adoptopenjdk/openjdk11:ubi

# Maintainer
LABEL maintainer="Lorine >>MONEYPAL Project"
LABEL maintainer="norineachieng769@gmail.com"
WORKDIR /MONEYPAL_PROJECT

ENV TZ=Africa/Nairobi
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY /target/billingservice-1.0.jar billingservice-1.0.jar
COPY config config
EXPOSE 6670
CMD ["java", "-jar", "billingservice-1.0.jar", "&"]