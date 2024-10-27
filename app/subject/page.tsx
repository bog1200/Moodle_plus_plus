import { useRouter } from 'next/router';

type SubjectAPIType = {
  type: string;
  name: string;
  description: string;
  startDate: string;
  endDate: string;
};

export default async function SubjectPage() {
  const router = useRouter();
  const { subjectId } = router.query;

  const response = await fetch(`https://mpp.romail.app/api/v1/assignment/subject/${subjectId}`, {
    mode: 'cors',
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
    },
  });
  const data: SubjectAPIType[] = await response.json();

  return (
    <div className="p-4">
      <h3 className="text-xl font-bold mb-4">List of assignments for this subject:</h3>
      {data.length === 0 ? (
        <p>There are no assignments for this subject currently.</p>
      ) : (
        data.map((item, index) => (
          <div key={index} className="mb-4 p-4 bg-gray-100 rounded-lg shadow-md">
            <p><strong>Type:</strong> {item.type}</p>
            <p><strong>Name:</strong> {item.name}</p>
            <p><strong>Description:</strong> {item.description}</p>
          </div>
        ))
      )}
    </div>
  );
}


// import { useEffect, useState } from 'react';
// import { useRouter } from 'next/router';
//
// const SubjectPage: React.FC = () => {
//   const router = useRouter();
//   const { subjectId } = router.query;
//
//   const [loading, setLoading] = useState(true);
//   const [subjectInfo, setSubjectInfo] = useState(null);
//   const [subjectAPI, setSubjectAPI] = useState(null);
//
//   useEffect(() => {
//     if (subjectId) {
//       const fetchSubjectAPI = async () => {
//         try {
//           const response = await fetch(`https://mpp.romail.app/api/v1/assignment/subject/${subjectId}`, {
//             mode: 'cors',
//             method: 'GET',
//             headers: {
//               'Content-Type': 'application/json',
//               'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
//             },
//           });
//           if (!response.ok) {
//             throw new Error(`HTTP error! status: ${response.status}`);
//           }
//           const data = await response.json();
//           const initialSubjectAPI = data.map(item => ({
//             type: item.type,
//             name: item.name,
//             description: item.description,
//             startDate: item.startDate,
//             endDate: item.endDate
//           }));
//           setSubjectAPI(initialSubjectAPI);
//           setLoading(false);
//         } catch (error) {
//           console.error('Failed to fetch subjects:', error);
//         }
//       };
//       fetchSubjectAPI();
//     }
//   }, [subjectId]);
//
//   let subjectAPILength = 0;
//   if (subjectAPI) {
//     subjectAPILength = subjectAPI.length;
//   }
//
//   return (
//     <div className="p-4">
//       <h3 className="text-xl font-bold mb-4">List of assignments for this subject:</h3>
//       {subjectAPILength === 0 ? (
//         <p>There are no assignments for this subject currently.</p>
//       ) : (
//         subjectAPI.map((item, index) => (
//           <div key={index} className="mb-4 p-4 bg-gray-100 rounded-lg shadow-md">
//             <p><strong>Type:</strong> {item.type}</p>
//             <p><strong>Name:</strong> {item.name}</p>
//             <p><strong>Description:</strong> {item.description}</p>
//           </div>
//         ))
//       )}
//     </div>
//   );
// };
//
// export default SubjectPage;