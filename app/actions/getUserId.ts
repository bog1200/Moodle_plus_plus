"use server"
import { auth } from "@/auth";
import { PrismaClient } from "@prisma/client";

export async function getUserId() {
    const session = await auth();

    if (!session) {
        throw new Error('Not authenticated');
    }

    const user = session.user.id!
    const prisma = new PrismaClient();

    const userBun = prisma.user.findFirst({
        where: {
            accounts:
                {
                    some: {
                        providerAccountId: user
                    }
                }
        }
    });
    console.log("USER ID IS: " + userBun);

    return userBun;
}