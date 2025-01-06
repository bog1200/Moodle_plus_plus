"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();


export async function deleteSubmission(submissionId: string) {
    // Fetch the file details from the database
    const deleteFiles =  prisma.file.deleteMany({
        where: {
            assignmentSubmissionId: submissionId,
        },
    });

    const deleteSubmission =  prisma.assignmentSubmission.delete({
        where: {
            id: submissionId,
        },
    });

    return  prisma.$transaction([deleteFiles, deleteSubmission]);


}