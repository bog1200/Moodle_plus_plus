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
    console.log(`getCurrentStudent: Session: ${JSON.stringify(session)}`);
    console.log(`getCurrentStudent: User: ${user}`);

    const student = await prisma.student.findFirst({
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
    if (student){
        console.log(`getCurrentStudent: Student: ${JSON.stringify(student)}`);
    }
    return student;
}