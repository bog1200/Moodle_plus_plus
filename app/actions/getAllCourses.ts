"use server";

import { PrismaClient } from '@prisma/client';
import {getCurrentStudent} from "@/app/actions/getCurrentStudent";

const prisma = new PrismaClient();
export async function getEnrolledCourses() {

    const student = await getCurrentStudent();

    if (!student) {
       return null;
    }
    // Fetch courses from the database
    const subjects =  await prisma.subjectEnrollment.findMany({
        where: {
            studentId: student.id
        },
        include: {
            subject: {
                include: {
                    teacher: {
                        include: {
                            user: {
                                select: {
                                    name: true,
                                }
                            }
                        }

                    },
                },
            },
        },
    });
    if (process.env.NODE_ENV === 'development') {
        console.log("SUBJECTS: " + JSON.stringify(subjects));
    }
    return subjects;
}