DO
$$
    BEGIN
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'group') THEN
            DROP TABLE "group" CASCADE;
            RAISE
                NOTICE 'Table "group" dropped.';
        ELSE
            RAISE NOTICE 'Table "group" does not exist.';
        END IF;
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'user') THEN
            DROP TABLE "user" CASCADE;
            RAISE
                NOTICE 'Table "user" dropped.';
        ELSE
            RAISE NOTICE 'Table "user" does not exist.';
        END IF;
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'directory') THEN
            DROP TABLE "directory" CASCADE;
            RAISE
                NOTICE 'Table "directory" dropped.';
        ELSE
            RAISE NOTICE 'Table "directory" does not exist.';
        END IF;
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'directory_access') THEN
            DROP TABLE "directory_access" CASCADE;
            RAISE
                NOTICE 'Table "directory_access" dropped.';
        ELSE
            RAISE NOTICE 'Table "directory_access" does not exist.';
        END IF;
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'file') THEN
            DROP TABLE "file" CASCADE;
            RAISE
                NOTICE 'Table "file" dropped.';
        ELSE
            RAISE NOTICE 'Table "file" does not exist.';
        END IF;
        IF
            EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'file_access') THEN
            DROP TABLE "file_access" CASCADE;
            RAISE
                NOTICE 'Table "file_access" dropped.';
        ELSE
            RAISE NOTICE 'Table "file_access" does not exist.';
        END IF;
    END
$$;


------------------------------------------------------------------------------------------------------------------ GROUP
CREATE TABLE "group"
(
    id          SERIAL PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    role        VARCHAR(50) NOT NULL,
    CONSTRAINT unique_group_name
        UNIQUE (name)
);

INSERT INTO "group" (name, role) VALUES ('Testgroup', 'Testrole');
INSERT INTO "group" (name, role) VALUES ('Testgroup2', 'Testrole2');

------------------------------------------------------------------------------------------------------------------- USER
CREATE TABLE "user"
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    group_id  INT          REFERENCES "group" (id) ON DELETE SET NULL,
    CONSTRAINT unique_user_email
        UNIQUE (email)
);

INSERT INTO "user" (firstname, lastname, email, password, group_id) VALUES ('Test', 'User', 'test.user@example.com', 'testpassword', 1);
INSERT INTO "user" (firstname, lastname, email, password, group_id) VALUES ('Test2', 'User2', 'test2.user2@example.xom', 'testpassword2', 2);

-------------------------------------------------------------------------------------------------------------- DIRECTORY
CREATE TABLE directory
(
    id           SERIAL PRIMARY KEY,
    path         TEXT NOT NULL,
    created_at   TIMESTAMP DEFAULT NOW(),
    created_by   INT NOT NULL,
    updated_at   TIMESTAMP DEFAULT NOW(),
    updated_by   INT NOT NULL,
    CONSTRAINT fk_created_by
        FOREIGN KEY (created_by)
            REFERENCES "user" (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES "user" (id)
            ON DELETE CASCADE,
    CONSTRAINT unique_directory_path
        UNIQUE (path)
);

INSERT INTO directory (path, created_by, updated_by) VALUES ('/path/to/testdirectory', 1, 1);
INSERT INTO directory (path, created_by, updated_by) VALUES ('/path/to/testdirectory2', 2, 2);

------------------------------------------------------------------------------------------------------- DIRECTORY ACCESS
CREATE TABLE directory_access
(
    id           SERIAL PRIMARY KEY,
    directory_id INT  NOT NULL,
    group_id     INT  NOT NULL,
    access_type  VARCHAR(3) NOT NULL,
    CONSTRAINT fk_directory
        FOREIGN KEY (directory_id)
            REFERENCES directory (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_group_id
        FOREIGN KEY (group_id)
            REFERENCES "group" (id)
            ON DELETE CASCADE,
    CONSTRAINT unique_directory_access
        UNIQUE (directory_id, group_id)
);

INSERT INTO directory_access (directory_id, group_id, access_type) VALUES (1, 1, 'rwx');
INSERT INTO directory_access (directory_id, group_id, access_type) VALUES (2, 2, 'rwx');

------------------------------------------------------------------------------------------------------------------ FILES
CREATE TABLE file
(
    id           SERIAL PRIMARY KEY,
    name         TEXT NOT NULL,
    directory_id INT  NOT NULL,
    created_at   TIMESTAMP DEFAULT NOW(),
    created_by   INT NOT NULL,
    updated_at   TIMESTAMP DEFAULT NOW(),
    updated_by   INT NOT NULL,
    CONSTRAINT fk_created_by
        FOREIGN KEY (created_by)
            REFERENCES "user" (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES "user" (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_directory
        FOREIGN KEY (directory_id)
            REFERENCES directory (id)
            ON DELETE CASCADE
);

INSERT INTO file (name, directory_id, created_by, updated_by) VALUES ('testfile.txt', 1, 1, 1);
INSERT INTO file (name, directory_id, created_by, updated_by) VALUES ('testfile2.txt', 2, 2, 2);

------------------------------------------------------------------------------------------------------------ FILE ACCESS
CREATE TABLE file_access
(
    id          SERIAL PRIMARY KEY,
    file_id     INT  NOT NULL,
    group_id    INT  NOT NULL,
    access_type VARCHAR(3) NOT NULL,
    CONSTRAINT fk_file
        FOREIGN KEY (file_id)
            REFERENCES file (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_group_id
        FOREIGN KEY (group_id)
            REFERENCES "group" (id)
            ON DELETE CASCADE,
    CONSTRAINT unique_file_access
        UNIQUE (file_id, group_id)
);

INSERT INTO file_access (file_id, group_id, access_type) VALUES (1, 1, 'rwx');
INSERT INTO file_access (file_id, group_id, access_type) VALUES (2, 2, 'rwx');