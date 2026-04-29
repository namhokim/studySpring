# batch6

Spring Batch 6 (Spring Boot 4.0) 학습 프로젝트.

## MySQL 실행 / 종료

로컬 MySQL은 Docker Compose 로 관리합니다.

### 실행

```bash
docker compose up -d
```

- 컨테이너 이름: `batch6-mysql`
- 포트: `3306`
- 계정: `root` / `mysql`
- 기본 데이터베이스: `batch6`

상태 확인:

```bash
docker compose ps
docker compose logs -f mysql
```

### 종료

컨테이너만 중지 (데이터 유지):

```bash
docker compose stop
```

컨테이너 제거 (볼륨은 유지):

```bash
docker compose down
```

데이터까지 완전히 삭제:

```bash
docker compose down -v
```

## 애플리케이션 실행

```bash
./gradlew bootRun
```
