 import {$Enums, PrismaClient} from '@prisma/client';
 import AssignmentType = $Enums.AssignmentType;

 const prisma = new PrismaClient();

 async function createEntries() {


    const account = await prisma.account.findFirst({
        where: {
            providerAccountId: '22db620a-0dc3-11ee-a70b-ca8b19b5aaf1'
        }
    })

    if (!account) {
        throw new Error('Account not found');
    }

     const user = await prisma.user.findFirst({
         where: {
             accounts: {
                 some: {
                     providerAccountId: account!.providerAccountId
                 }
             }
         }
     });

    const studentGroup = await prisma.studentGroup.create({
        data: {
            name: 'Group 1',
        }
    });

   const student =  await prisma.student.create({
        data: {
            userId: account!.userId!,
            groupId: studentGroup.id,
        }
    });

    const teacher = await prisma.teacher.create({
        data: {
            userId: account!.userId!,
        }
    });

    const subject = await prisma.subject.create({
        data: {
            name: 'Math',
            teacherId: teacher.id
        }
    });

     await prisma.subjectEnrollment.create({
        data: {
            studentId: student.id,
            subjectId: subject.id
        }
    });

    await prisma.file.create({
        data: {
            fileName: 'song1.mp3',
            fileType: "application/mp3",
            fileLink: "mpp/22db620a-0dc3-11ee-a70b-ca8b19b5aaf1/1731353978591-All i want for Christmas is You.mp3",
            ownerId: user!.id
    }
    });

    await prisma.file.create({
        data: {
            fileName: 'song2.mp3',
            fileType: "application/mp3",
            fileLink: "mpp/22db620a-0dc3-11ee-a70b-ca8b19b5aaf1/1731436857489-song.mp3",
            ownerId: user!.id
    }
    });

    const assignment1= await prisma.assignment.create({
        data: {
            subjectId: subject.id,
            title: 'Assignment 1',
            description: 'Assignment 1 description',
            dueDate: new Date(),
            assignmentType: AssignmentType.HOMEWORK,
        }
    });

    await prisma.assignmentSubmission.create({
        data: {
            assignmentId: assignment1.id,
            studentId: student.id,
            submissionDate: new Date(),
        }
    });

    await prisma.assignment.create({
        data: {
            subjectId: subject.id,
            title: 'Assignment 2',
            description: 'Assignment 2 description',
            dueDate: new Date(1738368000),
            assignmentType: AssignmentType.HOMEWORK,
        }
    });

 }



 createEntries()
     .catch(e => {
         throw e;
     })
     .finally(async () => {
         await prisma.$disconnect();
     });