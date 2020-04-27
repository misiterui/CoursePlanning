from bs4 import BeautifulSoup
import urllib.request


# 把网页URL存在变量里
urlpage = 'http://www.sfu.ca/students/calendar/2020/summer/courses.html'

# 获取网页内容，把HTML数据保存在page变量中
page = urllib.request.urlopen(urlpage)

# 用BeautifulSoup解析html数据，并保存在soup变量里
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
            data = data.getText()
            if len(data) > 1:
                rows.append(data)
print(rows)


def text_save(filename, data):  # filename为写入CSV文件的路径，data为要写入数据列表.
    file = open(filename, 'a')
    for i in range(len(data)):
        s = str(data[i]).replace('[', '').replace(']', '')  # 去除[],这两行按数据不同，可以选择
        s = s.replace("'", '')   # 去除单引号，逗号，每行末尾追加换行符
        file.write("<item>" + s + "</item>" + '\n')
    file.close()
    print("保存文件成功")


text_save('courses.txt', rows)



