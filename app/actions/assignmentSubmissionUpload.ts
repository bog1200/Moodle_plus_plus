"use server"
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function createAssignmentSubmission(studentId: string, assignmentId: string, fileUploadId: string) {
    // Create a new AssignmentSubmission

    const assignmentSubmission = await prisma.assignmentSubmission.create({
        data: {
            submissionDate: new Date(),
            student: {
                connect: {
                    id: studentId,
                },
            },
            assignment: {
                connect: {
                    id: assignmentId,
                },
            },
        },
    });

    // Create a new File and associate it with the created AssignmentSubmission and Assignment
    //instead of creating it, update it using the fileUploadId
    await prisma.file.update({
        where: {
            id: fileUploadId,
        },
        data: {
            owner: {
                connect: {
                    id: studentId,
                },
            },
            assignmentSubmission: {
                connect: {
                    id: assignmentSubmission.id,
                },
            }
        },
    });

    return assignmentSubmission.id;
}