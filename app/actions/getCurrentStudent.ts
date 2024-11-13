import {auth} from "@/auth";
import {PrismaClient} from "@prisma/client";

export async function getCurrentStudent(){

    const session = await auth();

    if(!session){
        return null;
    }

    const user = session?.user;

    if (!user) {
        return null;
    }

    const prisma = new PrismaClient();

    return prisma.student.findFirst({
        where: {
            user: {
                accounts: {
                    some: {
                        providerAccountId: user.id
                    }
                }
            }
        }
    });


}