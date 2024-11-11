import {auth} from "@/auth";
import {PrismaClient} from "@prisma/client";

export async function getSubjectEnrollment(){

    const session = await auth();

    if(!session){
        return null;
    }

    const user = session?.user;

    if (!user) {
        return null;
    }

    const prisma = new PrismaClient();

    const account = await prisma.account.findFirst({
        where: {
            providerAccountId: user.id
        }
    })

    if(!account){
        return null;
    }

    const student = await prisma.student.findMany({
        where: {
            accountId: account.id!
        }
    })

    //TODO: check-uri separate daca e doar un cont sau mai multe

    if (student == null)
    {
        return null;
    }

    const subjectEnrollment = await prisma.subjectEnrollment.findMany({
        where: {
            studentId: student.id!
        }
    })

    if (subjectEnrollment == null)
    {
        return null;
    }

    return subjectEnrollment;


}