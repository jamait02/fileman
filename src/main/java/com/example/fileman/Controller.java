package com.example.fileman;

import com.example.fileman.Directory.DirectoryAccess.DirectoryAccess;
import com.example.fileman.Directory.DirectoryAccess.DirectoryAccessService;
import com.example.fileman.Directory.Directory;
import com.example.fileman.Directory.DirectoryService;
import com.example.fileman.File.FileAccess.FileAccess;
import com.example.fileman.File.FileAccess.FileAccessService;
import com.example.fileman.File.File;
import com.example.fileman.File.FileService;
import com.example.fileman.Group.Group;
import com.example.fileman.Group.GroupService;
import com.example.fileman.User.User;
import com.example.fileman.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Returns a greeting message.
     *
     * @return The greeting message.
     *
     * @example
     *     // Retrieve the greeting message:
     *     String greeting = sayHello();
     *     // Output: "Hello world."
     */
    @GetMapping("/greeting")
    public String sayHello() {
        System.out.println("Backend is running... Hello world.");
        return "Hello world.";
    }

    //region User
    /**
     * Adds a new user to the PostgreSQL database with the provided details.
     *
     * @param firstname   The first name of the new user.
     * @param lastname    The last name of the new user.
     * @param email       The email address of the new user.
     * @param password    The password of the new user.
     * @param group_id     The ID of the group to which the new user belongs.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws DataAccessException if an error occurs while accessing the database.
     *
     * @example
     *     // Add a new user with the following details:
     *     // First Name: John
     *     // Last Name: Doe
     *     // Email: johndoe@example.com
     *     // Password: mypassword
     *     // Group ID: 1
     *     userService.addUser("John", "Doe", "johndoe@example.com", "mypassword", 1);
     */
    @PostMapping("/addUser")
    public int addUser(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("group_id") int group_id
    ) {
        return UserService.addUser(
                jdbcTemplate,
                firstname,
                lastname,
                email,
                password,
                group_id
        );
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param id The ID of the user to delete.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     *
     * @example
     *     // Delete a user with the following ID:
     *     // User ID: 1
     *     deleteUser(1);
     */
    @PostMapping("/deleteUser")
    public int deleteUser(
            @RequestParam("id") int id
    ) {
        return UserService.deleteUser(
                jdbcTemplate,
                id
        );
    }

    /**
     * Updates a user with the specified details.
     *
     * @param id         The ID of the user to update.
     * @param firstname  The updated first name of the user.
     * @param lastname   The updated last name of the user.
     * @param email      The updated email address of the user.
     * @param password   The updated password of the user.
     * @param group_id   The updated ID of the group to which the user belongs.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     *
     * @example
     *     // Update a user with the following details:
     *     // User ID: 1
     *     // First Name: John
     *     // Last Name: Doe
     *     // Email: johndoe@example.com
     *     // Password: mypassword
     *     // Group ID: 2
     *     updateUser(1, "John", "Doe", "johndoe@example.com", "mypassword", 2);
     */
    @PostMapping("/updateUser")
    public int updateUser(
            @RequestParam("id") int id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("group_id") int group_id
    ) {
        return UserService.updateUser(
                jdbcTemplate,
                id,
                firstname,
                lastname,
                email,
                password,
                group_id
        );
    }

    /**
     * Retrieves a user from the database based on the provided email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return The User object if the user is found, null otherwise.
     *
     * @example
     *     // Retrieve a user with the given email and password:
     *     // Email: johndoe@example.com
     *     // Password: mypassword
     *     User user = getUser("johndoe@example.com", "mypassword");
     */
    @GetMapping("/getUser")
    public User getUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        return UserService.getUserByEmailAndPassword(
                jdbcTemplate,
                email,
                password
        );
    }
    //endregion

    //region Group
    /**
     * Adds a new group with the specified name and role.
     *
     * @param name The name of the new group.
     * @param role       The role associated with the group.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the group_name or role is invalid or null.
     *
     * @example
     *     // Add a new group with the following details:
     *     // Group Name: Admins
     *     // Role: Administrator
     *     addGroup("Admins", "Administrator");
     */
    @PostMapping("/addGroup")
    public int addGroup(
            @RequestParam("name") String name,
            @RequestParam("role") String role
    ) {
        return GroupService.addGroup(
                jdbcTemplate,
                name,
                role
        );
    }

    /**
     * Deletes a group with the specified ID.
     *
     * @param id The ID of the group to delete.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     *
     * @example
     *     // Delete a group with the following ID:
     *     // Group ID: 1
     *     deleteGroup(1);
     */
    @PostMapping("/deleteGroup")
    public int deleteGroup(
            @RequestParam("id") int id
    ) {
        return GroupService.deleteGroup(
                jdbcTemplate,
                id
        );
    }

    /**
     * Updates a group with the specified details.
     *
     * @param id    The ID of the group to update.
     * @param name  The updated name of the group.
     * @param role  The updated role of the group.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     *
     * @example
     *     // Update a group with the following details:
     *     // Group ID: 1
     *     // Name: New Group Name
     *     // Role: Admin
     *     updateGroup(1, "New Group Name", "Admin");
     */
    @PostMapping("/updateGroup")
    public int updateGroup(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("role") String role
    ) {
        return GroupService.updateGroup(
                jdbcTemplate,
                id,
                name,
                role
        );
    }

    /**
     * Retrieves a group from the database based on the provided group ID.
     *
     * @param id The ID of the group to retrieve.
     * @return The Group object if the group is found, null otherwise.
     *
     * @example
     *     // Retrieve a group with the given group ID:
     *     // Group ID: 1
     *     Group group = getGroup(1);
     */
    @GetMapping("/getGroup")
    public Group getGroup(
            @RequestParam("id") int id
    ) {
        return GroupService.getGroupById(
                jdbcTemplate,
                id
        );
    }
    //endregion

    //region Directory
    /**
     * Adds a new directory with the specified path.
     *
     * @param path The path of the new directory.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the directory_path is invalid or null.
     *
     * @example
     *     // Add a new directory with the following path:
     *     // Directory Path: /home/user/documents
     *     addDirectory("/home/user/documents");
     */
    @PostMapping("/addDirectory")
    public int addDirectory(
            @RequestParam("path") String path,
            @RequestParam("created_by") int created_by,
            @RequestParam("updated_by") int updated_by
    ) {
        return DirectoryService.addDirectory(
                jdbcTemplate,
                path,
                created_by,
                updated_by
        );
    }

    /**
     * Deletes a directory with the specified ID.
     *
     * @param id The ID of the directory to delete.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     *
     * @example
     *     // Delete a directory with the following ID:
     *     // Directory ID: 1
     *     deleteDirectory(1);
     */
    @PostMapping("/deleteDirectory")
    public int deleteDirectory(
            @RequestParam("id") int id
    ) {
        return DirectoryService.deleteDirectory(
                jdbcTemplate,
                id
        );
    }

    /**
     * Updates a directory with the specified details.
     *
     * @param id          The ID of the directory to update.
     * @param path        The updated path of the directory.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     *
     * @example
     *     // Update a directory with the following details:
     *     // Directory ID: 1
     *     // Path: /new/path
     *     // Updated At: 2023-06-12T15:30:00Z
     *     updateDirectory(1, "/new/path", new Date(2023, 6, 12, 15, 30, 0));
     */
    @PostMapping("/updateDirectory")
    public int updateDirectory(
            @RequestParam("id") int id,
            @RequestParam("path") String path
    ) {
        return DirectoryService.updateDirectory(
                jdbcTemplate,
                id,
                path
        );
    }

    /**
     * Retrieves a directory from the database based on the provided directory ID.
     *
     * @param id The ID of the directory to retrieve.
     * @return The Directory object if the directory is found, null otherwise.
     *
     * @example
     *     // Retrieve a directory with the given directory ID:
     *     // Directory ID: 1
     *     Directory directory = getDirectory(1);
     */
    @GetMapping("/getDirectory")
    public Directory getDirectory(
            @RequestParam("id") int id
    ) {
        return DirectoryService.getDirectoryById(
                jdbcTemplate,
                id
        );
    }
    //endregion

    //region Directory Access
    /**
     * Adds directory access for a group with the specified directory ID and group ID.
     *
     * @param directory_id The ID of the directory to grant access to.
     * @param group_id     The ID of the group to grant access.
     * @param access_type  The access type granted to the goup_id (r- Read, w-Write, x-Execute)
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the directory_id or group_id is invalid or null.
     * @example // Grant access to a group for a directory with the following IDs:
     * // Directory ID: 1
     * // Group ID: 2
     * addDirectoryAccess(1, 2);
     */
    @PostMapping("/addDirectoryAccess")
    public int addDirectoryAccess(
            @RequestParam("directory_id") int directory_id,
            @RequestParam("group_id") int group_id,
            @RequestParam("access_type") String access_type
    ) {
        return DirectoryAccessService.addDirectoryAccess(
                jdbcTemplate,
                directory_id,
                group_id,
                access_type
        );
    }

    /**
     * Deletes directory access with the specified ID.
     *
     * @param group_id The ID of the Group of the selected directory.
     * @param directory_id The ID of the Directory which access will be deleted from.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     * @example // Delete directory access with the following ID:
     * // Directory Access ID: 1
     * deleteDirectoryAccess(1);
     */
    @PostMapping("/deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId")
    public int deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId(
            @RequestParam("group_id") int group_id,
            @RequestParam("directory_id") int directory_id
    ) {
        return DirectoryAccessService.deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId(
                jdbcTemplate,
                group_id,
                directory_id
        );
    }

    /**
     * Updates directory access with the specified details.
     *
     * @param group_id     The updated ID of the group associated with the directory access.
     * @param directory_id The updated ID of the directory associated with the directory access.
     * @param access_type  The updated access type of the directory access.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     * @example // Update directory access with the following details:
     * // Directory Access ID: 1
     * // Group ID: 2
     * // Directory ID: 3
     * // Access Type: Read-Write
     * updateDirectoryAccess(1, 2, 3, "Read-Write");
     */
    @PostMapping("/updateDirectoryAccessByDirectoryIdAndGroupId")
    public int updateDirectoryAccessByDirectoryIdAndGroupId(
            @RequestParam("group_id") int group_id,
            @RequestParam("directory_id") int directory_id,
            @RequestParam("access_type") String access_type
    ) {
        return DirectoryAccessService.updateDirectoryAccessByDirectoryIdAndGroupId(
                jdbcTemplate,
                directory_id,
                group_id,
                access_type
        );
    }

    @GetMapping("/getDirectoryAccessById")
    public DirectoryAccess getDirectoryAccessById(
            @RequestParam("id") int id
    ) {
        return DirectoryAccessService.getDirectoryAccessById(
                jdbcTemplate,
                id
        );
    }

    @GetMapping("getDirectoryAccessByDirectoryId")
    public List<DirectoryAccess> getDirectoryAccessByDirectoryId(
            @RequestParam("directory_id") int directory_id
    ) {
        return DirectoryAccessService.getDirectoryAccessByDirectoryId(
                jdbcTemplate,
                directory_id
        );
    }

    @GetMapping("getDirectoryAccessByGroupId")
    public List<DirectoryAccess> getDirectoryAccessByGroupId(
            @RequestParam("group_id") int group_id
    ) {
        return DirectoryAccessService.getDirectoryAccessByGroupId(
                jdbcTemplate,
                group_id
        );
    }

    @GetMapping("getDirectoryAccessByDirectoryIdAndGroupId")
    public DirectoryAccess getDirectoryAccessByDirectoryIdAndGroupId(
            @RequestParam("directory_id") int directory_id,
            @RequestParam("group_id") int group_id
    ) {
        return DirectoryAccessService.getDirectoryAccessByDirectoryIdAndGroupId(
                jdbcTemplate,
                directory_id,
                group_id
        );
    }
    //endregion

    //region File
    /**
     * Adds a new file with the specified details.
     *
     * @param name         The name of the new file.
     * @param directory_id The ID of the directory to which the new file belongs.
     * @param created_by   ID of the user who created the file.
     * @param updated_by   ID of the user who last updated the file.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     * @example // Add a new file with the following details:
     * // Name: myFile.txt
     * // Path: /myDirectory/myFile.txt
     * // Directory ID: 1
     * // Created At: 2023-06-12T10:00:00Z
     * // Updated At: 2023-06-12T12:30:00Z
     * addFile("myFile.txt", "/myDirectory/myFile.txt", 1, new Date(2023, 6, 12, 10, 0, 0), new Date(2023, 6, 12, 12, 30, 0));
     */
    @PostMapping("/addFile")
    public int addFile(
            @RequestParam("name") String name,
            @RequestParam("directory_id") int directory_id,
            @RequestParam("created_at") int created_by,
            @RequestParam("updated_at") int updated_by
    ) {
        return FileService.addFile(
                jdbcTemplate,
                name,
                directory_id,
                created_by,
                updated_by
        );
    }

    /**
     * Deletes a file with the specified ID.
     *
     * @param id The ID of the file to delete.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     * @example // Delete a file with the following ID:
     * // File ID: 1
     * deleteFile(1);
     */
    @PostMapping("/deleteFile")
    public int deleteFile(
            @RequestParam("id") int id
    ) {
        return FileService.deleteFile(
                jdbcTemplate,
                id
        );
    }

    /**
     * Updates a file with the specified details.
     *
     * @param id           The ID of the file to update.
     * @param name         The updated name of the file.
     * @param directory_id The updated ID of the directory to which the file belongs.
     * @param updated_by   The ID of the user who last updated the file.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     * @example // Update a file with the following details:
     * // File ID: 1
     * // Updated Name: updatedFile.txt
     * // Updated Path: /myDirectory/updatedFile.txt
     * // Updated Directory ID: 2
     * // Updated By User ID: 3
     * updateFile(1, "updatedFile.txt", "/myDirectory/updatedFile.txt", 2, 3);
     */
    @PostMapping("/updateFile")
    public int updateFile(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("directory_id") int directory_id,
            @RequestParam("updated_at") int updated_by
    ) {
        return FileService.updateFile(
                jdbcTemplate,
                id,
                name,
                directory_id,
                updated_by
        );
    }

    /**
     * Retrieves a file by its ID.
     *
     * @param id The ID of the file to retrieve.
     *
     * @return The file corresponding to the provided ID.
     *
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     *
     * @example
     *     // Retrieve a file with the following ID:
     *     // File ID: 1
     *     getFileById(1);
     */
    @GetMapping("/getFileById")
    public File getFileById(
            @RequestParam("id") int id
    ) {
        return FileService.getFileById(
                jdbcTemplate,
                id
        );
    }
    //endregion

    //region File Access
    /**
     * Adds access for a file to a specific group.
     *
     * @param file_id     The ID of the file to grant access to.
     * @param group_id    The ID of the group to grant access to.
     * @param access_type The type of access to grant (e.g., read, write, execute).
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     * @example // Grant access to the file with the following details:
     * // File ID: 1
     * // Group ID: 2
     * // Access Type: read
     * addFileAccess(1, 2, "read");
     */
    @PostMapping("/addFileAccess")
    public int addFileAccess(
            @RequestParam("file_id") int file_id,
            @RequestParam("group_id") int group_id,
            @RequestParam("access_type") String access_type
    ) {
        return FileAccessService.addFileAccess(
                jdbcTemplate,
                file_id,
                group_id,
                access_type
        );
    }

    /**
     * Deletes file access with the specified ID.
     *
     * @param group_id The Group ID of the file access to delete.
     * @param file_id  The File ID of the file access to delete.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     * @example // Delete file access with the following ID:
     * // File Access ID: 1
     * deleteFileAccess(1);
     */
    @PostMapping("/deleteFileAccessByFileIdAndGroupId")
    public int deleteFileAccessByFileIdAndGroupId(
            @RequestParam("group_id") int group_id,
            @RequestParam("file_id") int file_id
    ) {
        return FileAccessService.deleteFileAccessByFileIdAndGroupId(
                jdbcTemplate,
                group_id,
                file_id
        );
    }

    /**
     * Updates file access with the specified details.
     *
     * @param file_id     The File ID of the file access to be updated.
     * @param group_id    The Group ID of the file access to be updated.
     * @param access_type The updated type of access.
     * @return The number of rows affected. Should be 1 if successful.
     * @throws IllegalArgumentException if any of the provided parameters are invalid or null.
     * @example // Update file access with the following details:
     * // File Access ID: 1
     * // Updated File ID: 2
     * // Updated Group ID: 3
     * // Updated Access Type: write
     * updateFileAccess(1, 2, 3, "write");
     */
    @PostMapping("/updateFileAccessByFileIdAndGroupId")
    public int updateFileAccessByFileIdAndGroupId(
            @RequestParam("file_id") int file_id,
            @RequestParam("group_id") int group_id,
            @RequestParam("access_type") String access_type
    ) {
        return FileAccessService.updateFileAccessByFileIdAndGroupId(
                jdbcTemplate,
                file_id,
                group_id,
                access_type
        );
    }

    /**
     * Retrieves file access by its ID.
     *
     * @param id The ID of the file access to retrieve.
     *
     * @return The file access corresponding to the provided ID.
     *
     * @throws IllegalArgumentException if the provided ID is invalid or null.
     *
     * @example
     *     // Retrieve file access with the following ID:
     *     // File Access ID: 1
     *     getFileAccess(1);
     */
    @GetMapping("/getFileAccess")
    public FileAccess getFileAccess(
            @RequestParam("id") int id
    ) {
        return FileAccessService.getFileAccessById(
                jdbcTemplate,
                id
        );
    }
    //endregion
}
