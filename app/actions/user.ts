"use server";

// Import necessary modules
import {PrismaClient, User} from '@prisma/client';
import { auth } from '@/auth';

const prisma = new PrismaClient();


export async function getUserRoles() {

    const session = await auth();

    if(!session){
        return null;
    }

    const user = session?.user;

    if (!user) {
        return null
    }

    // Fetch course details from the database

    return prisma.user.findFirst({
        where: {
            accounts:
            {
                some: {
                    providerAccountId: user.id
                }
            }
        },
        select: {
            isAdmin: true,
            isTeacher: true,
            isStudent: true,
        }
    })
}

export async function getAllUsers() {
    return prisma.user.findMany({
        select: {
            id: true,
            name: true,
            email: true,
            isAdmin: true,
            isTeacher: true,
            isStudent: true,
        }
    });
}

export async function getUserById(id: string) {
    return prisma.user.findFirst({
        where: {
            id: id
        }
    });
}

export async function updateUser(user: User){
    return prisma.user.update({
        where: {
            id: user.id
        },
        data: {
            name: user.name,
            isAdmin: user.isAdmin,
            isTeacher: user.isTeacher,
            isStudent: user.isStudent
        }
    });
}