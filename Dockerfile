FROM bellsoft/liberica-openjre-alpine:17.0.12

# Install curl jq
RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh


# start the runner.sh
ENTRYPOINT sh runner.shg
