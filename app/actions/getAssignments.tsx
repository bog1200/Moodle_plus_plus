import {auth} from "@/auth";
import {PrismaClient} from "@prisma/client";
import {getSubjectEnrollment} from "@/app/actions/getSubjectEnrollment";

export async function getAssignments(){

    const session = await auth();

    if(!session){
        return null;
    }

    const user = session?.user;

    if (!user) {
        return null;
    }

    const subjectEnrollment = await getSubjectEnrollment()

    if (subjectEnrollment == null)
    {
        return null;
    }
    //TODO WHEN SUBJECT ID IS EQUAL TO SUBJECTENROLLMENT ID THEN RETURN ASSIGNMENTS
    // const subject = await prisma.subject.findMany({
    //     where: {
    //         id: subjectEnrollment.id
    //     }
    // })

    return subjectEnrollment;


}