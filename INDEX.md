---
title: "Software Methodologies — Index"
tags: [agile, waterfall, requirements, user-stories, api-design, spring-boot, flutter, testing, ci-cd, jwt]
level: intermediate
language: en
---

# Software Methodologies — Index

End-to-end process labs: from requirements to deployment.

## Labs (jump list)

| # | Topic | File | Extract these ideas |
| --- | --- | --- | --- |
| 2 | Intro & requirement analysis | [Lab2](Lab2_Introduction_Requirement_Analysis.md) | Agile vs Waterfall, user stories, Mermaid flowcharts |
| 3 | Architecture & API planning | [Lab3](Lab3_Architecture_Design_API_Planning.md) | API contracts, system design for the lab app |
| 4 | Spring Boot backend | [Lab4](Lab4_Backend_Development_Spring_Boot.md) | Controllers, services, persistence |
| 5 | Flutter frontend | [Lab5](Lab5_Frontend_Development_Flutter.md) | Client UI against the API |
| 6 | Testing, deployment, CI/CD | [Lab6](Lab6_Testing_Deployment_CICD.md) | Tests, pipelines, release |

Hub README: [README.md](README.md)

## Sample backend inventory

Path: [`backend/`](backend/)

| Concern | Location (under `backend/src/main/java/.../backend/`) |
| --- | --- |
| Security / JWT | `security/` (`JwtGenerator`, `JwtAuthenticationFilter`, …) |
| Config | `configuration/` (Firebase, OpenAPI, Jackson, Security) |
| Domain | `entity/user/` (`UserEntity`, `Task`, `UserType`) |
| Persistence | `repository/` |
| API | `controller/`, `service/` |

## Keyword index

- `agile`, `waterfall`, `scrum`, `kanban` → Lab2  
- `user story`, `acceptance criteria`, `mermaid` → Lab2  
- `REST`, `OpenAPI`, `API design` → Lab3  
- `Spring Boot`, `JPA`, `controller`, `service` → Lab4 + `backend/`  
- `Flutter`, `mobile client` → Lab5  
- `CI/CD`, `pipeline`, `deployment`, `testing strategy` → Lab6  
- `JWT`, `Firebase` → `backend/security`, `backend/configuration`

## Related

- Architecture theory: [../software_architecture/INDEX.md](../software_architecture/INDEX.md)
- Spring PPT (if extracted): [../academy/ppt/README.md](../academy/ppt/README.md)
- Parent docs: [docs/10-rest-http](../../docs/10-rest-http/README.md), [docs/21-cicd-devops](../../docs/21-cicd-devops/README.md)

Back to [Archive knowledge index](../README.md)
