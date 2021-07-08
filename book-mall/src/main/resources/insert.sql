INSERT INTO user_dest
SELECT
    id,
    username,
    password,
    email
FROM temp_table