"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';
import {getCurrentStudent} from "@/app/actions/getCurrentStudent";

const prisma = new PrismaClient();


export async function deleteSubmission(submissionId: string) {

   const student = await getCurrentStudent();
   if (!student) {
       throw new Error('Not authenticated');
    }
    const submission = await prisma.assignmentSubmission.findUnique({
        where: {
            id: submissionId,
        },
    });

    if (!submission) {
        throw new Error('Submission not found');
    }

    if (submission.studentId !== student.id) {
        throw new Error('You are not authorized to delete this submission');
    }
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