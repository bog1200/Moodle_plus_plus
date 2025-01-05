 import { PrismaClient } from '@prisma/client';

 const prisma = new PrismaClient();

 async function createEntries() {

    const account = await prisma.account.findFirst({
        where: {
            providerAccountId: '22db620a-0dc3-11ee-a70b-ca8b19b5aaf1'
        }
    })

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


 }


 createEntries()
     .catch(e => {
         throw e;
     })
     .finally(async () => {
         await prisma.$disconnect();
     });

