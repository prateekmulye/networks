# Synthetic Network Testing Microservice

A Spring Boot–based microservice that lets you define, schedule, and execute synthetic HTTP and ICMP tests, stream results to Kafka, persist them in PostgreSQL, and run containerized via Docker/Kubernetes. Ideal for showcasing Java, Kafka, Docker, and cloud-native skills—aligned with ThousandEyes’ network-intelligence mission.

---

## Table of Contents

1. [Overview](#overview)  
2. [Features](#features)  
3. [Architecture](#architecture)  
4. [Getting Started](#getting-started)  
   - [Prerequisites](#prerequisites)  
   - [Clone & Build](#clone--build)  
   - [Configuration](#configuration)  
   - [Run Locally](#run-locally)  
5. [Usage](#usage)  
6. [Running with Docker Compose](#running-with-docker-compose)  
7. [Testing](#testing)  
8. [Future Enhancements](#future-enhancements)  
9. [Contributing](#contributing)  
10. [License](#license)  

---

## Overview

This microservice allows you to:

- **Create & manage** synthetic test configurations (HTTP or ICMP).  
- **Schedule** periodic execution of these tests.  
- **Stream** each test result to Kafka for real-time processing.  
- **Persist** historical results in PostgreSQL for analysis.  
- **Expose** REST APIs for configuration and (optionally) result retrieval.  
- **Containerize** the entire stack with Docker and Kubernetes manifests.

---

## Features

- **REST API** for CRUD operations on test configurations  
- **Spring Scheduling** to trigger tests at configurable intervals  
- **HTTP & ICMP** measurement implementations  
- **Apache Kafka** producer & consumer integration  
- **Spring Data JPA** + **PostgreSQL** for storage  
- **Spring Boot Actuator** for health checks & metrics  
- **Dockerfile** and **docker-compose.yml** for local orchestration  
- **Kubernetes** manifests for cloud-native deployment  

---

## Architecture

```mermaid
flowchart TD
  subgraph Client
    A[User CLI / Dashboard]
  end

  subgraph Microservice
    B[REST API]
    C[TestExecutionService]
    D[Kafka Producer]
    E[Kafka Consumer]
    F[PostgreSQL]
  end

  subgraph Message Bus
    K[Apache Kafka]
  end

  A -->|Configure tests via HTTP| B
  B --> C
  C -->|HTTP/ICMP calls| Target[Network Targets]
  C -->|produce| D
  D --> K
  K --> E
  E --> F
  A -- optional: View results via REST --> B
