DO
$$
    BEGIN
        IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'group') THEN
            DROP TABLE "group" CASCADE ;
            RAISE NOTICE 'Table "group" dropped.';
        ELSE
            RAISE NOTICE 'Table "group" does not exist.';
        END IF;
        IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'user') THEN
            DROP TABLE "user" CASCADE ;
            RAISE NOTICE 'Table "user" dropped.';
        ELSE
            RAISE NOTICE 'Table "user" does not exist.';
        END IF;
        IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'directories') THEN
            DROP TABLE "directories" CASCADE ;
            RAISE NOTICE 'Table "directories" dropped.';
        ELSE
            RAISE NOTICE 'Table "directories" does not exist.';
        END IF;
        IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'directory_access') THEN
            DROP TABLE "directory_access" CASCADE ;
            RAISE NOTICE 'Table "directory_access" dropped.';
        ELSE
            RAISE NOTICE 'Table "directory_access" does not exist.';
        END IF;
        IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'files') THEN
            DROP TABLE "files" CASCADE ;
            RAISE NOTICE 'Table "files" dropped.';
        ELSE
            RAISE NOTICE 'Table "files" does not exist.';
        END IF;
        IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'file_access') THEN
            DROP TABLE "file_access" CASCADE ;
            RAISE NOTICE 'Table "file_access" dropped.';
        ELSE
            RAISE NOTICE 'Table "file_access" does not exist.';
        END IF;
    END
$$;


------------------------------------------------------------------------------------------------------------------ GROUP
CREATE TABLE "group"
(
    id         SERIAL PRIMARY KEY,
    group_name VARCHAR(50) NOT NULL,
    role       VARCHAR(50) NOT NULL
);

/**
 * Adds a new group with the specified group name and role.
 *
 * @param new_group_name  The name of the new group to be added.
 * @param new_role        The role assigned to the new group.
 *
 * @returns               Void.
 *
 * @example
 *    -- Add a new group with name 'Developers' and role 'Developer'
 *    SELECT add_group('Developers', 'Developer');
 */
CREATE OR REPLACE FUNCTION add_group(
    new_group_name VARCHAR(50),
    new_role VARCHAR(50)
)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO "group" (group_name, role)
    VALUES (new_group_name, new_role);
END;
$$ LANGUAGE plpgsql;

/**
 * Updates an existing group with the specified new group name and role.
 *
 * @param old_group_name  The name of the group to be updated.
 * @param new_group_name  The new name to assign to the group.
 * @param new_role        The new role to assign to the group.
 *
 * @returns               Void.
 *
 * @example
 *    -- Update the group named 'Admin' with new name 'SuperAdmin' and role 'Administrator'
 *    SELECT update_group('Admin', 'SuperAdmin', 'Administrator');
 */
CREATE OR REPLACE FUNCTION update_group(
    old_group_name VARCHAR(50),
    new_group_name VARCHAR(50),
    new_role VARCHAR(50)
)
    RETURNS VOID AS $$
BEGIN
    UPDATE "group"
    SET group_name = new_group_name, role = new_role
    WHERE group_name = old_group_name;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes an existing group with the specified group name.
 *
 * @param group_name_to_remove  The name of the group to be removed.
 *
 * @returns                     Void.
 *
 * @example
 *    -- Remove the group named 'Developers'
 *    SELECT remove_group('Developers');
 */
CREATE OR REPLACE FUNCTION remove_group(
    group_name_to_remove VARCHAR(50)
)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM "group"
    WHERE group_name = group_name_to_remove;
END;
$$ LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------- USER
CREATE TABLE "user"
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    group_id  INT          REFERENCES "group" (id) ON DELETE SET NULL
);

/**
 * Adds a new user with the specified details.
 *
 * @param new_firstname   The first name of the new user.
 * @param new_lastname    The last name of the new user.
 * @param new_email       The email address of the new user.
 * @param new_password    The password of the new user.
 * @param new_group_id    The ID of the group to which the new user belongs.
 *
 * @returns               Void.
 *
 * @example
 *    -- Add a new user with the following details:
 *    -- First Name: John
 *    -- Last Name: Doe
 *    -- Email: johndoe@example.com
 *    -- Password: mypassword
 *    -- Group ID: 1
 *    SELECT add_user('John', 'Doe', 'johndoe@example.com', 'mypassword', 1);
 */
CREATE OR REPLACE FUNCTION add_user(
    new_firstname VARCHAR(255),
    new_lastname VARCHAR(255),
    new_email VARCHAR(255),
    new_password VARCHAR(255),
    new_group_id INT
)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO "user" (firstname, lastname, email, password, group_id)
    VALUES (new_firstname, new_lastname, new_email, new_password, new_group_id);
END;
$$ LANGUAGE plpgsql;

/**
 * Updates an existing user with the specified details.
 *
 * @param user_id          The ID of the user to be updated.
 * @param new_firstname   The new first name for the user.
 * @param new_lastname    The new last name for the user.
 * @param new_email       The new email address for the user.
 * @param new_password    The new password for the user.
 * @param new_group_id    The new group ID for the user.
 *
 * @returns               Void.
 *
 * @example
 *    -- Update user with ID 1 with the following details:
 *    -- First Name: Jane
 *    -- Last Name: Smith
 *    -- Email: janesmith@example.com
 *    -- Password: newpassword
 *    -- Group ID: 2
 *    SELECT update_user(1, 'Jane', 'Smith', 'janesmith@example.com', 'newpassword', 2);
 */
CREATE OR REPLACE FUNCTION update_user(
    user_id INT,
    new_firstname VARCHAR(255),
    new_lastname VARCHAR(255),
    new_email VARCHAR(255),
    new_password VARCHAR(255),
    new_group_id INT
)
    RETURNS VOID AS $$
BEGIN
    UPDATE "user"
    SET
        firstname = new_firstname,
        lastname = new_lastname,
        email = new_email,
        password = new_password,
        group_id = new_group_id
    WHERE id = user_id;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes an existing user with the specified user ID.
 *
 * @param user_id          The ID of the user to be removed.
 *
 * @returns               Void.
 *
 * @example
 *    -- Remove user with ID 1
 *    SELECT remove_user(1);
 */
CREATE OR REPLACE FUNCTION remove_user(
    user_id INT
)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM "user"
    WHERE id = user_id;
END;
$$ LANGUAGE plpgsql;


-------------------------------------------------------------------------------------------------------------- DIRECTORY
CREATE TABLE directories (
                             id SERIAL PRIMARY KEY,
                             user_id INT NOT NULL,
                             directory_path TEXT NOT NULL,
                             created_at TIMESTAMP DEFAULT NOW(),
                             CONSTRAINT fk_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES "user" (id)
                                     ON DELETE CASCADE
);

/**
 * Creates a new directory for the specified user.
 *
 * @param user_id          The ID of the user for whom to create the directory.
 * @param directory_path   The path of the new directory.
 *
 * @returns               Void.
 *
 * @example
 *    -- Create a new directory for user with ID 1
 *    -- Directory Path: '/home/user1/mydir'
 *    SELECT create_directory(1, '/home/user1/mydir');
 */
CREATE OR REPLACE FUNCTION create_directory(
    user_id INT,
    directory_path TEXT
)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO directories (user_id, directory_path)
    VALUES (user_id, directory_path);
END;
$$ LANGUAGE plpgsql;

/**
 * Updates the path of an existing directory.
 *
 * @param p_directory_id         The ID of the directory to update.
 * @param p_new_directory_path   The new path for the directory.
 *
 * @returns                      Void.
 *
 * @example
 *    -- Update the path of directory with ID 1
 *    -- New Directory Path: '/home/user1/newdir'
 *    SELECT update_directory(1, '/home/user1/newdir');
 */
CREATE OR REPLACE FUNCTION update_directory(
    p_directory_id INT,
    p_new_directory_path TEXT
)
    RETURNS VOID AS $$
BEGIN
    UPDATE directories
    SET directory_path = p_new_directory_path
    WHERE id = p_directory_id;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes a directory and its associated files and access permissions.
 *
 * @param p_directory_id   The ID of the directory to remove.
 *
 * @returns                Void.
 *
 * @example
 *    -- Remove directory with ID 1 and its associated files and access permissions
 *    SELECT remove_directory(1);
 */
CREATE OR REPLACE FUNCTION remove_directory(
    p_directory_id INT
)
    RETURNS VOID AS $$
DECLARE
    v_file_ids INT[];
BEGIN
    -- Get file IDs associated with the directory
    SELECT ARRAY(SELECT id FROM files WHERE directory_id = p_directory_id) INTO v_file_ids;

    -- Delete files associated with the directory
    DELETE FROM files WHERE directory_id = p_directory_id;

    -- Delete the directory
    DELETE FROM directories WHERE id = p_directory_id;

    -- Delete access permissions for the directory
    DELETE FROM directory_access WHERE directory_id = p_directory_id;

    -- Delete access permissions for the files
    DELETE FROM file_access WHERE file_id = ANY(v_file_ids);
END;
$$ LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------- DIRECTORY ACCESS
CREATE TABLE directory_access (
                                  id SERIAL PRIMARY KEY,
                                  directory_id INT NOT NULL,
                                  user_id INT NOT NULL,
                                  access_type TEXT NOT NULL,
                                  CONSTRAINT fk_directory
                                      FOREIGN KEY (directory_id)
                                          REFERENCES directories (id)
                                          ON DELETE CASCADE,
                                  CONSTRAINT fk_user
                                      FOREIGN KEY (user_id)
                                          REFERENCES "user" (id)
                                          ON DELETE CASCADE
);

/**
 * Adds or updates directory access for a user.
 *
 * @param p_user_id         The ID of the user.
 * @param p_directory_id    The ID of the directory.
 * @param p_access_type     The type of access to grant (e.g., 'read', 'write', 'admin').
 *
 * @returns                 Void.
 *
 * @example
 *    -- Grant 'read' access to user with ID 1 for directory with ID 1
 *    SELECT add_directory_access(1, 1, 'read');
 */
CREATE OR REPLACE FUNCTION add_directory_access(
    p_user_id INT,
    p_directory_id INT,
    p_access_type TEXT
)
    RETURNS VOID AS $$
BEGIN
    -- Check if the directory access already exists for the user
    IF EXISTS (
        SELECT 1
        FROM directory_access
        WHERE user_id = p_user_id AND directory_id = p_directory_id
    ) THEN
        -- Directory access already exists, update the access type
        UPDATE directory_access
        SET access_type = p_access_type
        WHERE user_id = p_user_id AND directory_id = p_directory_id;
    ELSE
        -- Directory access doesn't exist, insert a new record
        INSERT INTO directory_access (user_id, directory_id, access_type)
        VALUES (p_user_id, p_directory_id, p_access_type);
    END IF;
END;
$$ LANGUAGE plpgsql;

/**
 * Updates directory access for a user.
 *
 * @param p_user_id         The ID of the user.
 * @param p_directory_id    The ID of the directory.
 * @param p_access_type     The type of access to update (e.g., 'read', 'write', 'admin').
 *
 * @returns                 Void.
 *
 * @example
 *    -- Update 'write' access to user with ID 1 for directory with ID 1
 *    SELECT update_directory_access(1, 1, 'write');
 */
CREATE OR REPLACE FUNCTION update_directory_access(
    p_user_id INT,
    p_directory_id INT,
    p_access_type TEXT
)
    RETURNS VOID AS $$
BEGIN
    -- Check if the directory access exists for the user
    IF EXISTS (
        SELECT 1
        FROM directory_access
        WHERE user_id = p_user_id AND directory_id = p_directory_id
    ) THEN
        -- Directory access exists, update the access type
        UPDATE directory_access
        SET access_type = p_access_type
        WHERE user_id = p_user_id AND directory_id = p_directory_id;
    ELSE
        -- Directory access doesn't exist, do nothing or handle the situation accordingly
        -- You can choose to raise an exception or perform any other required action
        -- based on your business logic
    END IF;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes directory access for a user.
 *
 * @param p_user_id         The ID of the user.
 * @param p_directory_id    The ID of the directory.
 *
 * @returns                 Void.
 *
 * @example
 *    -- Remove directory access for user with ID 1 from directory with ID 1
 *    SELECT remove_directory_access(1, 1);
 */
CREATE OR REPLACE FUNCTION remove_directory_access(
    p_user_id INT,
    p_directory_id INT
)
    RETURNS VOID AS $$
BEGIN
    -- Check if the directory access exists for the user
    IF EXISTS (
        SELECT 1
        FROM directory_access
        WHERE user_id = p_user_id AND directory_id = p_directory_id
    ) THEN
        -- Directory access exists, remove the access
        DELETE FROM directory_access
        WHERE user_id = p_user_id AND directory_id = p_directory_id;
    ELSE
        -- Directory access doesn't exist, do nothing or handle the situation accordingly
        -- You can choose to raise an exception or perform any other required action
        -- based on your business logic
    END IF;
END;
$$ LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------------ FILES
CREATE TABLE files (
                       id SERIAL PRIMARY KEY,
                       user_id INT NOT NULL,
                       directory_id INT NOT NULL,
                       file_name TEXT NOT NULL,
                       file_path TEXT NOT NULL,
                       created_at TIMESTAMP DEFAULT NOW(),
                       CONSTRAINT fk_user
                           FOREIGN KEY (user_id)
                               REFERENCES "user" (id)
                               ON DELETE CASCADE,
                       CONSTRAINT fk_directory
                           FOREIGN KEY (directory_id)
                               REFERENCES directories (id)
                               ON DELETE CASCADE
);

/**
 * Creates a new file in a directory.
 *
 * @param user_id        The ID of the user creating the file.
 * @param directory_id   The ID of the directory where the file will be stored.
 * @param file_name      The name of the file.
 * @param file_path      The path of the file.
 *
 * @returns              Void.
 *
 * @example
 *    -- Create a new file in directory with ID 1 for user with ID 1
 *    SELECT create_file(1, 1, 'example.txt', '/path/to/example.txt');
 */
CREATE OR REPLACE FUNCTION create_file(
    user_id INT,
    directory_id INT,
    file_name TEXT,
    file_path TEXT
)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO files (user_id, directory_id, file_name, file_path)
    VALUES (user_id, directory_id, file_name, file_path);
END;
$$ LANGUAGE plpgsql;

/**
 * Updates the details of a file.
 *
 * @param file_id        The ID of the file to update.
 * @param new_file_name  The new name for the file.
 * @param new_file_path  The new path for the file.
 *
 * @returns              Void.
 *
 * @example
 *    -- Update the details of file with ID 1
 *    SELECT update_file(1, 'new_example.txt', '/new/path/to/example.txt');
 */
CREATE OR REPLACE FUNCTION update_file(
    file_id INT,
    new_file_name TEXT,
    new_file_path TEXT
)
    RETURNS VOID AS $$
BEGIN
    UPDATE files
    SET file_name = new_file_name,
        file_path = new_file_path
    WHERE id = file_id;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes a file from the database.
 *
 * @param file_id  The ID of the file to remove.
 *
 * @returns        Void.
 *
 * @example
 *    -- Remove the file with ID 1
 *    SELECT remove_file(1);
 */
CREATE OR REPLACE FUNCTION remove_file(file_id INT)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM files WHERE id = file_id;
END;
$$ LANGUAGE plpgsql;


------------------------------------------------------------------------------------------------------------ FILE ACCESS
CREATE TABLE file_access (
                             id SERIAL PRIMARY KEY,
                             file_id INT NOT NULL,
                             user_id INT NOT NULL,
                             access_type TEXT NOT NULL,
                             CONSTRAINT fk_file
                                 FOREIGN KEY (file_id)
                                     REFERENCES files (id)
                                     ON DELETE CASCADE,
                             CONSTRAINT fk_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES "user" (id)
                                     ON DELETE CASCADE
);

/**
 * Adds file access permission for a user to a specific file.
 *
 * @param file_id     The ID of the file.
 * @param user_id     The ID of the user.
 * @param access_type The type of access granted to the user.
 *
 * @returns           Void.
 *
 * @example
 *    -- Grant read access to file with ID 1 for user with ID 10
 *    SELECT add_file_access(1, 10, 'read');
 */
CREATE OR REPLACE FUNCTION add_file_access(
    file_id INT,
    user_id INT,
    access_type TEXT
)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO file_access (file_id, user_id, access_type)
    VALUES (file_id, user_id, access_type);
END;
$$ LANGUAGE plpgsql;

/**
 * Updates the access type for a user's file access permission.
 *
 * @param file_id_input     The ID of the file.
 * @param user_id_input     The ID of the user.
 * @param new_access_type   The new access type to be set.
 *
 * @returns                Void.
 *
 * @example
 *    -- Update the access type to 'write' for user with ID 10 for file with ID 1
 *    SELECT update_file_access(1, 10, 'write');
 */
CREATE OR REPLACE FUNCTION update_file_access(
    file_id_input INT,
    user_id_input INT,
    new_access_type TEXT
)
    RETURNS VOID AS $$
BEGIN
    UPDATE file_access
    SET access_type = new_access_type
    WHERE file_id = file_id_input
      AND user_id = user_id_input;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes a user's file access permission for a specific file.
 *
 * @param file_id_input   The ID of the file.
 * @param user_id_input   The ID of the user.
 *
 * @returns              Void.
 *
 * @example
 *    -- Remove file access for user with ID 10 for file with ID 1
 *    SELECT remove_file_access(1, 10);
 */
CREATE OR REPLACE FUNCTION remove_file_access(
    file_id_input INT,
    user_id_input INT
)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM file_access
    WHERE file_id = file_id_input
      AND user_id = user_id_input;
END;
$$ LANGUAGE plpgsql;

/**
 * Adds multiple files to a directory.
 *
 * @param directory_id   The ID of the directory.
 * @param user_id        The ID of the user adding the files.
 * @param file_paths     An array of file paths.
 * @param file_names     An array of file names.
 *
 * @returns              Void.
 *
 * @example
 *    -- Add files to directory with ID 1 for user with ID 10
 *    SELECT add_files_to_directory(1, 10, ARRAY['/path/to/file1', '/path/to/file2'], ARRAY['file1.txt', 'file2.txt']);
 */
CREATE OR REPLACE FUNCTION add_files_to_directory(
    directory_id INT,
    user_id INT,
    file_paths TEXT[],
    file_names TEXT[]
)
    RETURNS VOID AS $$
DECLARE
    i INT := 1;
BEGIN
    WHILE i <= array_length(file_paths, 1) AND i <= 10 LOOP
        -- Perform the necessary operations to store the file externally
        -- For example, you can use a file system or object storage service

        -- Here, we're just printing the file information as a placeholder
            RAISE NOTICE 'Adding file: % (% bytes)', file_names[i], pg_stat_file(file_paths[i])#>>'{size}';

            -- Insert the file metadata into the files table
            INSERT INTO files (directory_id, user_id, file_name, file_path)
            VALUES (directory_id, user_id, file_names[i], file_paths[i]);

            i := i + 1;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

/**
 * Updates multiple files in a directory.
 *
 * @param directory_id     The ID of the directory.
 * @param file_ids         An array of file IDs to update.
 * @param new_file_paths   An array of new file paths.
 * @param new_file_names   An array of new file names.
 *
 * @returns                Void.
 *
 * @example
 *    -- Update files in directory with ID 1, providing the respective file IDs, new file paths, and new file names
 *    SELECT update_files_in_directory(1, ARRAY[10, 11], ARRAY['/new/path/to/file1', '/new/path/to/file2'], ARRAY['new_file1.txt', 'new_file2.txt']);
 */
CREATE OR REPLACE FUNCTION update_files_in_directory(
    directory_id INT,
    file_ids INT[],
    new_file_paths TEXT[],
    new_file_names TEXT[]
)
    RETURNS VOID AS $$
DECLARE
    i INT := 1;
BEGIN
    WHILE i <= array_length(file_ids, 1) LOOP
        -- Perform the necessary operations to update the file externally
        -- For example, you can use a file system or object storage service

        -- Here, we're just printing the updated file information as a placeholder
            RAISE NOTICE 'Updating file ID %: %', file_ids[i], new_file_names[i];

            -- Update the file metadata in the files table
            UPDATE files
            SET file_name = new_file_names[i], file_path = new_file_paths[i]
            WHERE id = file_ids[i] AND directory_id = directory_id;

            i := i + 1;
        END LOOP;
END;
$$ LANGUAGE plpgsql;

/**
 * Removes multiple files from a directory.
 *
 * @param p_directory_id   The ID of the directory.
 * @param p_file_ids       An array of file IDs to remove.
 *
 * @returns                Void.
 *
 * @example
 *    -- Remove files with IDs 10 and 11 from the directory with ID 1
 *    SELECT remove_files_from_directory(1, ARRAY[10, 11]);
 */
CREATE OR REPLACE FUNCTION remove_files_from_directory(
    p_directory_id INT,
    p_file_ids INT[]
)
    RETURNS VOID AS $$
BEGIN
    -- Delete the files from the files table
    DELETE FROM files
    WHERE directory_id = p_directory_id AND id = ANY(p_file_ids);

    -- Perform any necessary cleanup or file removal operations externally

    RAISE NOTICE 'Removed files with IDs: %', p_file_ids;
END;
$$ LANGUAGE plpgsql;