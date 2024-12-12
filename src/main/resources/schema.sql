CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
    );

-- Opcional: Adicionar índices se necessário
CREATE INDEX IF NOT EXISTS idx_users_name ON users(name);