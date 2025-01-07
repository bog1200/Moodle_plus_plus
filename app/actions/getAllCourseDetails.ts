"use server";

// Import necessary modules
import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

export async function getAllCourseDetails(course_id: string) {
    // Fetch course details from the database
    const courseDetails = await prisma.subject.findUnique({
        where: {
            id: course_id,
        },
        include: {
            teacher: {
                include: {
                    user: true,
                },
            },
            assignments: {
                include: {
                    submissions: true,
                    grade: true,
                },
            },
            // course: {
            //
            // }
        },
    });

    return courseDetails;
}