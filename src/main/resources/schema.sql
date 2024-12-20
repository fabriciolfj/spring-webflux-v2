CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
    );

-- Opcional: Adicionar índices se necessário
CREATE INDEX IF NOT EXISTS idx_users_name ON users(name);

CREATE TABLE IF NOT EXISTS items (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price DOUBLE PRECISION NOT NULL
);

-- Opcional: Se quiser que o id seja auto-incrementado
-- ALTER TABLE items ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

-- Índices sugeridos
CREATE INDEX IF NOT EXISTS idx_items_name ON items(name);

-- Comentários na tabela e colunas (opcional, mas recomendado)
COMMENT ON TABLE items IS 'Tabela que armazena informações sobre itens';
COMMENT ON COLUMN items.id IS 'Identificador único do item';
COMMENT ON COLUMN items.name IS 'Nome do item';
COMMENT ON COLUMN items.price IS 'Preço do item';