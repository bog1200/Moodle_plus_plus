import { auth } from '@/auth';
import { PrismaClient } from '@prisma/client';

export async function getAllUserData() {
    const session = await auth();

    if (!session) {
        return null;
    }

    const user = session?.user;

    if (!user) {
        return null;
    }

    const prisma = new PrismaClient();

    return prisma.user.findFirst({
        where: {
            accounts:
                {
                    some: {
                        providerAccountId: user.id
                    }
                }
        }
    });

}