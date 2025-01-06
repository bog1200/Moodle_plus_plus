"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function deleteSubmission(file_id: string) {
    // Fetch the file details from the database
    const file = await prisma.file.findUnique({
        where: {
            id: file_id,
        },
    });

    if (file) {
        // Check if the file has an associated assignment submission
        if (file.assignmentSubmissionId) {
            // Delete the associated assignment submission
            await prisma.assignmentSubmission.delete({
                where: {
                    id: file.assignmentSubmissionId,
                },
            });
        }

        // Delete the file
        await prisma.file.delete({
            where: {
                id: file_id,
            },
        });
    }
}