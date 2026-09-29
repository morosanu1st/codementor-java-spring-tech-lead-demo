# Java Spring Tech Lead Mentoring Demo

Small Spring Boot microservice skeleton used to demonstrate how I mentor engineers stepping into a **Technical Lead** role with Java, Spring Boot, and microservices.

## What this demo shows

- A minimal **order-service** style Spring Boot app with clear package boundaries
- Controllers / services / models separated the way I'd expect a lead to keep a service readable
- Config and health endpoints as teaching hooks for production readiness
- Notes below on architecture habits, code review, and 1:1 mentoring style

## Project layout

```
src/main/java/com/demo/orderservice/
  OrderServiceApplication.java
  controller/  — HTTP boundary (keep thin)
  service/     — business rules + orchestration
  model/       — domain DTOs / entities
  config/      — Spring configuration
src/main/resources/application.yml
```

## How I mentor tech-lead skills

### Architecture & boundaries
- Start from the problem, not the framework: what owns the data? what is the public contract?
- Draw service boundaries around business capabilities, not around shared tables
- Prefer synchronous HTTP only where latency/consistency require it; otherwise events or async jobs
- Keep controllers dumb: validate input, call a service, map the response

### Spring Boot habits
- Constructor injection over field injection
- Explicit configuration over magic defaults when behavior matters in prod
- Profiles for local / staging / prod; never hardcode secrets
- Actuator health + readiness as a baseline for ops trust

### Microservices trade-offs I walk through
- When a modular monolith is the better first step
- Coupling via shared libraries vs. published APIs
- Idempotency, retries, and timeouts at the edges
- Observability: correlation IDs, structured logs, metrics before fancy tracing

### Code review habits for leads
- Review for clarity and failure modes first, style second
- Ask "what happens when this dependency is down?"
- Prefer small PRs with a clear narrative in the description
- Leave actionable comments; pair on the tricky ones instead of drive-by nits

### Mentoring style (1:1 / long-term)
- Short agenda: goal → blockers → one deep dive → next commitment
- Teach by pairing on a real slice of work, then have you drive the next slice alone
- Build a shared checklist (design doc, API contract, test strategy, rollout)
- Measure progress by decisions you can own, not by hours watched

## Run locally

```bash
# Requires JDK 17+
./mvnw spring-boot:run
# or after packaging:
java -jar target/order-service-0.0.1-SNAPSHOT.jar
```

Example:

```bash
curl http://localhost:8080/api/orders/demo-1
curl http://localhost:8080/actuator/health
```

## Who this is for

Engineers moving into tech lead responsibilities who want structured practice on Spring/microservices design, review culture, and mentoring habits — not just syntax help.

---

*Codementor mentoring demo — topic/skill sample only.*
