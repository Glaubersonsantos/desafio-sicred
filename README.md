# 📌 Projeto: API de Aquisição de Crédito

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **OpenFeign**
* **Resilience4j (Circuit Breaker)**
* **PostgreSQL**
* **H2 (para testes)**
* **Spring Boot Actuator**
* **Swagger / OpenAPI**
* **Docker / Docker Compose**

---

## 🧠 Decisões de Arquitetura

O projeto foi desenvolvido seguindo princípios de **Clean Architecture** e boas práticas de engenharia de software:

### 🔹 Separação de camadas

* **Controller** → entrada da aplicação (API REST)
* **Application (Service)** → regras de negócio
* **Domain** → entidades e contratos
* **Infrastructure** → integrações externas e persistência

---

### 🔹 Uso de Gateway

Foi utilizado o padrão **Gateway** para desacoplar a comunicação com serviços externos:

* Permite trocar facilmente a tecnologia (Feign, WebClient, etc)
* Facilita testes (mock do gateway)
* Mantém o domínio independente da infraestrutura

---

### 🔹 Integração com Feign + Resilience4j

* **Feign** para comunicação HTTP com serviço externo
* **Circuit Breaker** para resiliência
* **Fallback** para evitar falhas em cascata

---

### 🔹 Strategy + Chain of Responsibility

Aplicado para validações de regras de negócio:

* Facilitação da extensão de regras
* Código desacoplado e de fácil manutenção

---

### 🔹 Observabilidade

* **Spring Boot Actuator** para monitoramento da aplicação

---

## 📋 Requisitos

Para executar o projeto, é necessário ter instalado:

* **Docker**
* **Git**

---

## ▶️ Como rodar o projeto

### 🔹 Clonar o repositório

```bash id="v5n8ch"
git clone https://github.com/Glaubersonsantos/desafio-sicred.git
cd desafio-sicred
```

---

### 🔹 Subir a aplicação

```bash id="ff3p6y"
docker-compose up --build
```

---

## 🌐 Acessos importantes

### 📘 Swagger (Documentação da API)

```plaintext id="4qk1sc"
http://localhost:8080/swagger-ui.html
```

---

### 📊 Actuator (Observabilidade)

#### Health (status da aplicação)

```plaintext id="r1r5gf"
http://localhost:8080/actuator/health
```

---

#### Métricas

```plaintext id="jpmndc"
http://localhost:8080/actuator/metrics
```

---

#### Informações da aplicação

```plaintext id="1g41uv"
http://localhost:8080/actuator/info
```

---

## ✅ Funcionalidades principais

* Contratação de crédito
* Validação por produto e segmento
* Persistência de operações
* Consulta de operações por ID
* Integração com serviço externo
* Resiliência com Circuit Breaker

---
