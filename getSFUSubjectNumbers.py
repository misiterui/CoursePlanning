from bs4 import BeautifulSoup
import urllib.request
import re


def text_save(filename, data):  # filename为写入CSV文件的路径，data为要写入数据列表.
    file = open(filename, 'a')
    for i in range(len(data)):
        s = str(data[i]).replace('[', '').replace(']', '')  # 去除[],这两行按数据不同，可以选择
        s = s.replace("'", '')  # 去除单引号，每行末尾追加换行符
        file.write(s + '\n')
    file.close()
    print("保存文件成功")


def getCourseNumberForOneSubject(urlpage):
    # 获取网页内容，把HTML数据保存在page变量中
    page = urllib.request.urlopen(urlpage)
    # 用BeautifulSoup解析html数据，并保存在soup变量里
    soup = BeautifulSoup(page, 'html.parser')

    section = soup.find('section', attrs={'class': 'main'})
    h3 = section.find_all('h3')
    rows = []
    for i in range(len(h3)):
        data = h3[i].getText()
        data = data.strip().replace('\xc9','').replace('\n\t\t\n\t\t\t',' ')
        if data=='Please note:':
            continue
        data = data.split(' ', 3)
        rows.append(data)
    rows.sort()
    print(rows)
    text_save('allCourses.txt', rows)


def getAllUrl():
    urlAll = 'http://www.sfu.ca/students/calendar/2020/summer/courses.html'
    page = urllib.request.urlopen(urlAll)
    soup = BeautifulSoup(page, 'html.parser')
    div = soup.find('div', attrs={'class': 'course-finder'})
    ul1 = div.find_all('ul')
    # 创建一个列表对象，并且把表头数据作为列表的第一个元素
    rows = []
    # 遍历所有数据
    for ul2 in ul1:
        for li in ul2:
            # 如果该单元格无数据，则跳过
            if len(li) == 0:
                continue
            data = li.find('a')
            if data != -1:
                href = data['href']
                if len(data.getText()) > 1:
                    href = "http://www.sfu.ca" + href
                    rows.append(href)
                    print(href)
    text_save("allUrls.txt", rows)


def read_all_pages():
    f = open("allUrls.txt")
    for line in f:
        getCourseNumberForOneSubject(line)
    f.close()


getAllUrl()
read_all_pages()
