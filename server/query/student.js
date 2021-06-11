const connection = require('../db');
const status = require('./status');

module.exports = {
    create: (id, pw) => {
        connection.query(`
            INSERT INTO STUDENT (STUDENT_ID, STUDENT_PW)
            VALUES ("${id}", "${pw}")
            `,
            
            (err, result) => {
                if(err) return status.fail;
                return status.success;
            }
        );
    },

    read: (id) => {
        connection.query(`
            SELECT *
            FROM STUDENT
            WHERE STUDENT_ID = "${id}"
            `,
            
            (err, result) => {
                if (err) return status.fail;
                const resultArray = Object.values(JSON.parse(JSON.stringify(result)))
                return resultArray;
            }
        );
    },

    update: (id, pw) => {
        connection.query(`
            UPDATE STUDENT
            SET STUDENT_PW = "${pw}"
            WHERE STUDENT_ID = "${id}"
            `,
    
            (err, result) => {
                if (err) return status.fail;
                return status.success;
            }
        );
    },

    delete: (id) => {
        connection.query(`
            DELETE FROM STUDENT
            WHERE STUDENT_ID = "${id}"
            `,
    
            (err, result) => {
                if (err) return status.fail;
                return status.success;
            }
    
        );
    }
}