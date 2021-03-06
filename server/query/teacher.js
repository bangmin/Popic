const connection = require('../db');
const status = require('./status');

module.exports = {
    create: (id, pw) => {
        connection.query(`
            INSERT INTO TEACHER (TEACHER_ID, TEACHER_PW)
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
            FROM TEACHER
            WHERE TEACHER_ID = "${id}"
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
            UPDATE TEACHER
            SET TEACHER_PW = "${pw}"
            WHERE TEACHER_ID = "${id}"
            `,
    
            (err, result) => {
                if (err) return status.fail;
                return status.success;
            }
        );
    },

    delete: (id) => {
        connection.query(`
            DELETE
            FROM TEACHER
            WHERE TEACHER_ID = "${id}"
            `,
    
            (err, result) => {
                if (err) return status.fail;
                return status.success;
            }
    
        );
    }
}