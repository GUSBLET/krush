CREATE DEFINER = CURRENT_USER TRIGGER trigger_delete_expired_tokens
AFTER INSERT OR UPDATE OR DELETE ON tokens
    FOR EACH ROW
BEGIN
DECLARE account_id_temp INT;

DELETE FROM accounts
WHERE id IN (
    SELECT account_id FROM tokens
    WHERE operation_type = 'CONFIRMING_ACCOUNT' AND lifetime < NOW()
);

DELETE FROM tokens WHERE lifetime < NOW();
END;
