# Starter de Microsserviços (Biblioteca)

## Subir tudo
```bash
docker compose build
docker compose up -d
```

## Portas
- Config Server: http://localhost:8888
- Eureka: http://localhost:8761
- Gateway: http://localhost:8080
- Catalog: http://localhost:8081 (Swagger em /swagger-ui.html)
- Reservations: http://localhost:8082 (Swagger em /swagger-ui.html)
- Notifier: http://localhost:8083
- RabbitMQ UI: http://localhost:15672 (guest/guest)
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000
- Zipkin: http://localhost:9411

## Fluxo rápido
1) Criar livro no Catalog:
   ```bash
   curl -X POST http://localhost:8081/api/livros -H "Content-Type: application/json"           -d '{"titulo":"DDD","autor":"Evans","ano":2003,"disponivel":false}'
   ```
2) Criar reserva no Reservations (vai para fila PENDENTE):
   ```bash
   curl -X POST "http://localhost:8082/api/reservas/1?aluno=Leonardo"
   ```
3) Processar devolução (promove primeiro da fila e publica evento):
   ```bash
   curl -X POST http://localhost:8082/api/reservas/livros/1/processar-devolucao
   ```
4) Ver no Notifier log de consumo do evento.
