# 🛒 Product API - Spring Boot CRUD

This repository contains the source code of a **RESTful API** built with **Java 17**, **Spring Boot 3.4.5**, and **PostgreSQL 14**, featuring authentication via **Spring Security**.  
The application implements a **product registration system** with support for **filtering**, **sorting**, and **categorization**.

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.4.5
  - Spring Web
  - Spring Data JPA
  - Spring Security
- PostgreSQL 14
- Gradle

## 📦 Features

- Full CRUD for products
- Filtering by name, availability, and category
- Dynamic sorting by name or other fields
- Product association with categories
- Security through basic authentication with Spring Security

## ⚙️ How to Run

### 1. Prerequisites

- Java 17 installed
- PostgreSQL 14 running
- Gradle installed (or use the `./gradlew` wrapper)

### 2. Create the Database

In PostgreSQL:

```sql
CREATE DATABASE product_db;
