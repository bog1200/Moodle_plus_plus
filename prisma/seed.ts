// import { PrismaClient } from '@prisma/client';
//
// const prisma = new PrismaClient();
//
// async function createEntries() {
//     const newAccount = await prisma.account.create({
//         data: {
//             ssoId: '5fe64736-a940-4180-946b-aaa37adf84a5',
//             firstName: 'Alexandru',
//             lastName: 'Soponaru',
//             email: 'alexandru.soponaru@gmail.com',
//             phone: '0728521521',
//             address: 'Str. Mihai Eminescu, nr. 1, Bucuresti',
//             gender: 'Male',
//             dob: new Date('2002-09-18')
//         },
//     });
//
//     const newStudent = await prisma.student.create({
//         data: {
//             accountId: newAccount.id
//         },
//     });
//
//     console.log('New account created:', newAccount);
//     console.log('New student created:', newStudent);
// }
//
// createEntries()
//     .catch(e => {
//         throw e;
//     })
//     .finally(async () => {
//         await prisma.$disconnect();
//     });