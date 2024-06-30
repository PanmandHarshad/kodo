-- Create Form table if not exists
CREATE TABLE IF NOT EXISTS form (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

-- Create Field table if not exists
CREATE TABLE IF NOT EXISTS field (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(255) NOT NULL,
    is_required BOOLEAN,
    type VARCHAR(50),
    configuration TEXT,
    form_id BIGINT,
    FOREIGN KEY (form_id) REFERENCES form (id)
);

