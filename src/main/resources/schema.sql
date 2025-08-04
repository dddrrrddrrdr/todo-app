DROP TABLE IF EXISTS "tasks";
DROP TABLE IF EXISTS "users";

CREATE TABLE "users"
(
    "id"    int NOT NULL,
    "name"  text,
    "email" text,
    "role"  text,
    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "tasks"
(
    "task_id"  int NOT NULL,
    "title"    text,
    "owner_id" int,
    CONSTRAINT "tasks_pkey" PRIMARY KEY ("task_id"),
    CONSTRAINT "fk_owner" FOREIGN KEY (owner_id) REFERENCES users (id)
);
