-- V1__init.sql

CREATE TABLE payments (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    idempotency_key   VARCHAR(255) UNIQUE NOT NULL,
    merchant_id       UUID NOT NULL,
    amount            NUMERIC(19,4) NOT NULL,
    currency          VARCHAR(3) NOT NULL DEFAULT 'INR',
    status            VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    description       TEXT,
    metadata          JSONB,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),

    -- Inline check constraint replaces payment_status ENUM
    CONSTRAINT chk_payment_status CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED'))
);

CREATE TABLE ledger_entries (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payment_id      UUID NOT NULL REFERENCES payments(id),
    merchant_id     UUID NOT NULL,
    entry_type      VARCHAR(15) NOT NULL,
    amount          NUMERIC(19,4) NOT NULL,
    balance_after   NUMERIC(19,4) NOT NULL,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),

    -- Inline check constraint replaces entry_type ENUM
    CONSTRAINT chk_ledger_entry_type CHECK (entry_type IN ('DEBIT', 'CREDIT', 'REFUND'))
);

CREATE TABLE webhook_logs (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payment_id      UUID NOT NULL REFERENCES payments(id),
    merchant_id     UUID NOT NULL,
    url             TEXT NOT NULL,
    payload         JSONB NOT NULL,
    status_code     INT,
    attempt         INT NOT NULL DEFAULT 1,
    delivered       BOOLEAN NOT NULL DEFAULT false,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Performance Indexes
CREATE INDEX idx_payments_merchant    ON payments(merchant_id);
CREATE INDEX idx_payments_status      ON payments(status);
CREATE INDEX idx_ledger_payment       ON ledger_entries(payment_id);
CREATE INDEX idx_webhook_payment      ON webhook_logs(payment_id);

-- NOTE: idx_payments_idempotency was removed because UNIQUE columns are auto-indexed!