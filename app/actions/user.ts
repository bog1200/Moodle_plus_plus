"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';
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