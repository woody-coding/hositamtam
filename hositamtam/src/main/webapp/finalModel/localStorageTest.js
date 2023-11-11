function init() {
    const memberInfo = window.localStorage.getItem('memberInfo');
    if (memberInfo) {
        const member = JSON.parse(memberInfo);
        console.log('id:', member.id);
        console.log('nickname:', member.nickname);
    } else {
        console.log('로컬 스토리지에 멤버 정보가 없습니다.');
    }
}

window.addEventListener('load', init);