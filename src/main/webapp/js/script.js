const submitBtn = document.getElementById('submit_btn');
const term = document.getElementById('term');
const termError = document.getElementById('term_error');
const code = document.getElementById('code');
const codeError1 = document.getElementById('code_error1');
const codeError2 = document.getElementById('code_error2');
const courseName = document.getElementById('name');
const courseNameError1 = document.getElementById('name_error1');
const courseNameError2 = document.getElementById('name_error2');
const grade = document.getElementById('grade');
const gradeError = document.getElementById('grade_error');

if (submitBtn !== null) {
	code.addEventListener('blur', () => {
		if (code.value === '') {
			codeError2.style.display = 'none';
		} else if (!code.value.match(/^[A-Z0-9\s]{9}$/)) {
			codeError2.style.display = 'block';
		} else {
			codeError2.style.display = 'none';
		}
	});
	
	courseName.addEventListener('blur', () => {
		if (courseName.value === '') {
			courseNameError2.style.display = 'none';
		} else if (!courseName.value.match(/^[a-zA-Z0-9\s]{,100}$/)) {
			courseNameError2.style.display = 'block';
		} else {
			courseNameError2.style.display = 'none';
		}
	});
	
	submitBtn.addEventListener('click', (event) => {
		if (term.value === '') {
			termError.style.display = 'block';
		} else {
			termError.style.display = 'none';
		}
		
		if (code.value === '') {
			codeError1.style.display = 'block';
		} else {
			codeError1.style.display = 'none';
		}
		
		if (courseName.value === '') {
			courseNameError1.style.display = 'block';
		} else {
			courseNameError1.style.display = 'none';
		}
		
		if (grade.value === '') {
			gradeError.style.display = 'block';
		} else {
			gradeError.style.display = 'none';
		}
		
		if (term.value === '') {
			term.focus();
		} else if (code.value === '') {
			code.focus();
		} else if (!code.value.match(/^[A-Z0-9\s]{9}$/)) {
			code.focus();
		} else if (courseName.value === '') {
			courseName.focus();
		} else if (!courseName.value.match(/^[a-zA-Z0-9\s]{,100}$/)) {
			courseName.focus();
		} else if (grade.value === '') {
			grade.focus();
		} else {
			if (!confirm('Are you sure to add this?')) {
				event.preventDefault();
			}
		}
	});
}
