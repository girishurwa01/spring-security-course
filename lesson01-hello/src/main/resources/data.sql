-- =====================================================================
--  LESSON 7 — TASK E: PUT YOUR USERS INTO THE DATABASE
--
--  Spring runs this SQL file every time the app starts.
--  H2 lives in memory, so the table is created fresh on every start
--  and these rows are inserted again.
--
--  TODO E: Copy the two BCrypt hashes from SecurityConfig.java
--          (girish = line ~74, boss = line ~79) and paste each one
--          in place of PASTE_..._HASH. Keep the 'single quotes'.
-- =====================================================================

INSERT INTO app_user (username, password, role) VALUES ('girish', '$2a$10$XFM.lJkyeZGiHLUUDtczwO.7XZEKZFwBG7vBwe8.hzOPxu6I35AE2', 'USER');
INSERT INTO app_user (username, password, role) VALUES ('boss',   '$2a$10$SRKOtbevGWAZnET88cZKOeeBidcHgtj04pwIK0JPE/8TFbi0nZX2q',   'ADMIN');
