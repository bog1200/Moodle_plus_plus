"use server"
import {auth} from "@/auth";
import {PrismaClient} from "@prisma/client";

export async function getCurrentStudent(){

    const session = await auth();
    const prisma = new PrismaClient();

    if(!session){
        throw new Error('Not authenticated');
    }

    const user = session.user.id!;

    const student = prisma.student.findFirst({
        where: {
            user: {
                accounts: {
                    some: {
                        providerAccountId: user
                    }
                }
            }
        }
    });
    //console.log("STUDENT IS: " + student);
    return student;
}