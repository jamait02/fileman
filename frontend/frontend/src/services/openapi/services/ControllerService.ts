/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Directory } from '../models/Directory';
import type { DirectoryAccess } from '../models/DirectoryAccess';
import type { File } from '../models/File';
import type { FileAccess } from '../models/FileAccess';
import type { Group } from '../models/Group';
import type { User } from '../models/User';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class ControllerService {

    /**
     * @param id
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @param groupId
     * @returns number OK
     * @throws ApiError
     */
    public static updateUser(
        id: number,
        firstname: string,
        lastname: string,
        email: string,
        password: string,
        groupId: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/updateUser',
            query: {
                'id': id,
                'firstname': firstname,
                'lastname': lastname,
                'email': email,
                'password': password,
                'group_id': groupId,
            },
        });
    }

    /**
     * @param id
     * @param name
     * @param role
     * @returns number OK
     * @throws ApiError
     */
    public static updateGroup(
        id: number,
        name: string,
        role: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/updateGroup',
            query: {
                'id': id,
                'name': name,
                'role': role,
            },
        });
    }

    /**
     * @param id
     * @param name
     * @param directoryId
     * @param updatedAt
     * @returns number OK
     * @throws ApiError
     */
    public static updateFile(
        id: number,
        name: string,
        directoryId: number,
        updatedAt: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/updateFile',
            query: {
                'id': id,
                'name': name,
                'directory_id': directoryId,
                'updated_at': updatedAt,
            },
        });
    }

    /**
     * @param fileId
     * @param groupId
     * @param accessType
     * @returns number OK
     * @throws ApiError
     */
    public static updateFileAccessByFileIdAndGroupId(
        fileId: number,
        groupId: number,
        accessType: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/updateFileAccessByFileIdAndGroupId',
            query: {
                'file_id': fileId,
                'group_id': groupId,
                'access_type': accessType,
            },
        });
    }

    /**
     * @param id
     * @param path
     * @returns number OK
     * @throws ApiError
     */
    public static updateDirectory(
        id: number,
        path: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/updateDirectory',
            query: {
                'id': id,
                'path': path,
            },
        });
    }

    /**
     * @param groupId
     * @param directoryId
     * @param accessType
     * @returns number OK
     * @throws ApiError
     */
    public static updateDirectoryAccessByDirectoryIdAndGroupId(
        groupId: number,
        directoryId: number,
        accessType: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/updateDirectoryAccessByDirectoryIdAndGroupId',
            query: {
                'group_id': groupId,
                'directory_id': directoryId,
                'access_type': accessType,
            },
        });
    }

    /**
     * @param id
     * @returns number OK
     * @throws ApiError
     */
    public static deleteUser(
        id: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/deleteUser',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param id
     * @returns number OK
     * @throws ApiError
     */
    public static deleteGroup(
        id: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/deleteGroup',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param id
     * @returns number OK
     * @throws ApiError
     */
    public static deleteFile(
        id: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/deleteFile',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param groupId
     * @param fileId
     * @returns number OK
     * @throws ApiError
     */
    public static deleteFileAccessByFileIdAndGroupId(
        groupId: number,
        fileId: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/deleteFileAccessByFileIdAndGroupId',
            query: {
                'group_id': groupId,
                'file_id': fileId,
            },
        });
    }

    /**
     * @param id
     * @returns number OK
     * @throws ApiError
     */
    public static deleteDirectory(
        id: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/deleteDirectory',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param groupId
     * @param directoryId
     * @returns number OK
     * @throws ApiError
     */
    public static deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId(
        groupId: number,
        directoryId: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/deleteDirectoryAccessByDirectoryByDirectoryIdAndGroupId',
            query: {
                'group_id': groupId,
                'directory_id': directoryId,
            },
        });
    }

    /**
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @param groupId
     * @returns number OK
     * @throws ApiError
     */
    public static addUser(
        firstname: string,
        lastname: string,
        email: string,
        password: string,
        groupId: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/addUser',
            query: {
                'firstname': firstname,
                'lastname': lastname,
                'email': email,
                'password': password,
                'group_id': groupId,
            },
        });
    }

    /**
     * @param name
     * @param role
     * @returns number OK
     * @throws ApiError
     */
    public static addGroup(
        name: string,
        role: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/addGroup',
            query: {
                'name': name,
                'role': role,
            },
        });
    }

    /**
     * @param name
     * @param directoryId
     * @param createdBy
     * @param updatedBy
     * @returns number OK
     * @throws ApiError
     */
    public static addFile(
        name: string,
        directoryId: number,
        createdBy: number,
        updatedBy: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/addFile',
            query: {
                'name': name,
                'directory_id': directoryId,
                'created_by': createdBy,
                'updated_by': updatedBy,
            },
        });
    }

    /**
     * @param fileId
     * @param groupId
     * @param accessType
     * @returns number OK
     * @throws ApiError
     */
    public static addFileAccess(
        fileId: number,
        groupId: number,
        accessType: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/addFileAccess',
            query: {
                'file_id': fileId,
                'group_id': groupId,
                'access_type': accessType,
            },
        });
    }

    /**
     * @param path
     * @param createdBy
     * @param updatedBy
     * @returns number OK
     * @throws ApiError
     */
    public static addDirectory(
        path: string,
        createdBy: number,
        updatedBy: number,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/addDirectory',
            query: {
                'path': path,
                'created_by': createdBy,
                'updated_by': updatedBy,
            },
        });
    }

    /**
     * @param directoryId
     * @param groupId
     * @param accessType
     * @returns number OK
     * @throws ApiError
     */
    public static addDirectoryAccess(
        directoryId: number,
        groupId: number,
        accessType: string,
    ): CancelablePromise<number> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/addDirectoryAccess',
            query: {
                'directory_id': directoryId,
                'group_id': groupId,
                'access_type': accessType,
            },
        });
    }

    /**
     * @returns string OK
     * @throws ApiError
     */
    public static sayHello(): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/greeting',
        });
    }

    /**
     * @param email
     * @param password
     * @returns User OK
     * @throws ApiError
     */
    public static getUser(
        email: string,
        password: string,
    ): CancelablePromise<User> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getUser',
            query: {
                'email': email,
                'password': password,
            },
        });
    }

    /**
     * @param id
     * @returns Group OK
     * @throws ApiError
     */
    public static getGroup(
        id: number,
    ): CancelablePromise<Group> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getGroup',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param fileIds
     * @returns File OK
     * @throws ApiError
     */
    public static getFilesByFileIds(
        fileIds: Array<number>,
    ): CancelablePromise<Array<File>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getFilesByFileIds',
            query: {
                'file_ids': fileIds,
            },
        });
    }

    /**
     * @param id
     * @returns File OK
     * @throws ApiError
     */
    public static getFileById(
        id: number,
    ): CancelablePromise<File> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getFileById',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param id
     * @returns FileAccess OK
     * @throws ApiError
     */
    public static getFileAccessByGroupId(
        id: number,
    ): CancelablePromise<Array<FileAccess>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getFileAccessByGroupId',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param id
     * @returns Directory OK
     * @throws ApiError
     */
    public static getDirectory(
        id: number,
    ): CancelablePromise<Directory> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectory',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param path
     * @returns Directory OK
     * @throws ApiError
     */
    public static getDirectoryByPath(
        path: string,
    ): CancelablePromise<Directory> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectoryByPath',
            query: {
                'path': path,
            },
        });
    }

    /**
     * @param id
     * @returns DirectoryAccess OK
     * @throws ApiError
     */
    public static getDirectoryAccessById(
        id: number,
    ): CancelablePromise<DirectoryAccess> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectoryAccessById',
            query: {
                'id': id,
            },
        });
    }

    /**
     * @param groupId
     * @returns DirectoryAccess OK
     * @throws ApiError
     */
    public static getDirectoryAccessByGroupId(
        groupId: number,
    ): CancelablePromise<Array<DirectoryAccess>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectoryAccessByGroupId',
            query: {
                'group_id': groupId,
            },
        });
    }

    /**
     * @param directoryId
     * @returns DirectoryAccess OK
     * @throws ApiError
     */
    public static getDirectoryAccessByDirectoryId(
        directoryId: number,
    ): CancelablePromise<Array<DirectoryAccess>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectoryAccessByDirectoryId',
            query: {
                'directory_id': directoryId,
            },
        });
    }

    /**
     * @param directoryId
     * @param groupId
     * @returns DirectoryAccess OK
     * @throws ApiError
     */
    public static getDirectoryAccessByDirectoryIdAndGroupId(
        directoryId: number,
        groupId: number,
    ): CancelablePromise<DirectoryAccess> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectoryAccessByDirectoryIdAndGroupId',
            query: {
                'directory_id': directoryId,
                'group_id': groupId,
            },
        });
    }

    /**
     * @param directoryIds
     * @returns Directory OK
     * @throws ApiError
     */
    public static getDirectoriesByDirectoryIds(
        directoryIds: Array<number>,
    ): CancelablePromise<Array<Directory>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/getDirectoriesByDirectoryIds',
            query: {
                'directory_ids': directoryIds,
            },
        });
    }

}
