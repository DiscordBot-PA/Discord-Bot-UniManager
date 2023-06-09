# Discord-Bot-PA
This project provides a comprehensive system for managing university courses, grading, preferences, timetable and more. The main functionality of the system is described below.

## Table of Contents
- Grading System
- Preference System
- Timetable Management
- Quiz System
- RSS Feed
- Timetable Queries
- Grading System

### Commands

#### Grading system

- /addGrade
This functionality allows the addition of grades to a student's record.

  - Restrictions:
      - Only a professor can add grades.
      - A valid registration number of the student must be provided.
      - The grade value should be between 1 and 10 inclusive.
      - The courses that can be graded are Programming Advanced (PA) and Introduction to Programming (IP).
      
- /showGrade
This function allows you to view the grades of a student.
  - Validation:
      - A valid registration number of the student must be provided.

#### Preference System

- /addPreference
This functionality allows setting preferences for class schedules.
  - Validations:
      - The day of the week should be either Monday or Tuesday.
      - The class hour should be either 8, 10, or 12.
      - The course should be either Programming Advanced (PA) or Introduction to Programming (IP).
      - The group should be either B4, A5, or Course.

- /showPreference
This function allows you to view the set preferences.

#### Timetable Management
- /generateTimetable
This function generates a timetable based on the set preferences.

- /showTimetable
This function allows you to view the generated timetable.

Warning:
If the timetable has not been generated yet, a warning message will be shown.

#### Quiz System
- /startQuiz
This function allows you to start a quiz.

  - Validation:
      - The course should be either Programming Advanced (PA) or Introduction to Programming (IP).

- /submitQuiz
This function allows you to submit a completed quiz.

  - Validation:
      - The course should be either Programming Advanced (PA) or Introduction to Programming (IP).
#### RSS Feed
The system provides multiple RSS feeds which can be accessed with the !rss command followed by the name of the feed:
      - !dev
      - !gaming
      - !js
      - !net
      - !python
      - !react
      - !spring

#### Timetable Queries

- /asktimetable function allows you to ask various questions related to the timetable. It accepts up to 5 optional parameters to answer the following questions:
  - When does group X have course Y? (Provide: group + subject)
  - What is the timetable of course X? (Provide: subject)
  - What is the timetable of group X? (Provide: group)
  - What do I have on day X at hour Y? (Provide: day + hour)
  - What classes does professor X have on day Y? (Provide: teacher + day)
