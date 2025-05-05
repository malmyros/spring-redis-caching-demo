-- Create the cards table
CREATE TABLE IF NOT EXISTS cards(
    id BIGINT NOT NULL PRIMARY KEY,
    token VARCHAR(30) NOT NULL CHECK (token <> ''),
    card_status VARCHAR(16) NOT NULL CHECK (card_status <> ''),
    card_form_type VARCHAR(10) NOT NULL CHECK (card_form_type <> ''),
    embossed_name VARCHAR(50) NOT NULL CHECK (embossed_name <> ''),
    masked_pan VARCHAR(20) NOT NULL CHECK (masked_pan <> ''),
    expiry_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    version SMALLINT DEFAULT 0 NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE NULL,
    deleted_reason VARCHAR(255) NULL
);

COMMENT ON TABLE cards IS 'The customer cards';
COMMENT ON COLUMN cards.id IS 'The card unique id generated using TSID';
COMMENT ON COLUMN cards.token IS 'The public token of the card';
COMMENT ON COLUMN cards.card_status IS 'The status of the card';
COMMENT ON COLUMN cards.card_form_type IS 'The form of the card to identify whether its physical or virtual';
COMMENT ON COLUMN cards.embossed_name IS 'The customer stamped or raised name on the card';
COMMENT ON COLUMN cards.masked_pan IS 'The card masked PAN (primary account number)';
COMMENT ON COLUMN cards.expiry_date IS 'The date time the card is due to expire';
COMMENT ON COLUMN cards.created_at IS 'The date time the card was issued';
COMMENT ON COLUMN cards.updated_at IS 'The date time the card was updated';
COMMENT ON COLUMN cards.version IS 'The property is used for optimistic locking mechanism with JPA';
COMMENT ON COLUMN cards.deleted_at IS 'The date time the card was marked for deletion';
COMMENT ON COLUMN cards.deleted_reason IS 'The reason why the card was marked for deletion';
