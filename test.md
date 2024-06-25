#복습하기
1) 터미널을 사용해서 폴더만들기
    - cd Doucuments : Documents 라는 파일로 들어간다
    - mkdir testFoler : testFoler 라는 파일을 만든다
    - cd ./ testFoler : testFoler 라는 파일로 들어간다
    - touch test.md : test.md 라는 파일을 만든다
    - open . : 그 파일을 연다

2) github 와 연동하기
    - git config --global user.name "jinny"
    - git config --global user.email eujin.mstory@gmail.com
    - git commit -m "first commit"
    - git branch -M main
    - git remote add origin main url


3) 내가 어떻게 했지?
    - 새로운 레파지토리 생성  
        - 레파지토리 : 내 vs code 와 깃허브의 연결고리?
    - remote -v 로 나의 레파지토리가 잘 생성되어 있는지 확인
    - 여기서 바로 git psuh -u origin main 했더니 에러뜸
    - git commit -m "first commit" 
    - 갑자기 커밋되면서 올라감. -> 레파지토리를 연결하고나서야 commit이 됨.

4) 로컬에서 수정하고 다시 올리고 싶어?
    - 그러면 수정하고
    - 저장하고
    - git add .
    - git status
    - git commit -m "third commit"
    - git push origin main


    