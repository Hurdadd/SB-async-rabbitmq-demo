# Mini Spring Boot - RabbitMQ Demo

A simple **Order Service** demo project built with **Spring Boot** and **RabbitMQ**.  
It shows how to send orders through RabbitMQ and process them asynchronously with consumers,  
including **Dead Letter Queue (DLQ)** handling.

---

## Features
- REST API to create orders
- Async producer with RabbitMQ
- Consumer with concurrency control
- MapStruct for DTO ↔ Entity mapping
- Dead Letter Queue (DLQ) support
- Exception handling
- Docker Compose setup for RabbitMQ & MongoDB
- Swagger UI for API docs

---

## Architecture
[Client] -> [REST API] -> [Producer] -> [RabbitMQ Exchange] -> [Queue] -> [Consumer] -> [Database]
↘ (on error) -> [DLQ]

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### Run with Docker
```bash
docker compose up -d
```
### Example Usage

## Create an order:
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"userId": "u123", "amount": 1000.10, "product": "Phone"}'
```
