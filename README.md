[![Maintenance](https://img.shields.io/badge/Maintained%3F-no-red.svg)](https://github.com/ShivikaSodhi/sps-demo/graphs/commit-activity)
[![PR](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/ShivikaSodhi/sps-demo/pulls)
[![contrib](https://img.shields.io/badge/contributions-welcome-orange.svg)](https://github.com/ShivikaSodhi/sps-demo)


# SPS Demo
## Description
**The SPS Demo takes a server-sent events stream as input and performs processing on it. It sanitizes the data and computes various metrics aggregated over 1 second. 

The code incorporates easy addition of new sources, processing logic and data sinks.**


## Prerequisite Services/Applications for the service
* https://tweet-service.herokuapp.com/sps


## Getting Started/Requirements/Prerequisites/Dependencies

Install MVN Dependencies
```
mvn clean install
```

Run the project
```
mvn spring-boot:run
```
