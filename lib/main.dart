import 'package:flutter/material.dart';


void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',

      ///是否显示右上角debug标志，默认显示
//      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: Colors.white,
        primaryColorDark: Colors.white,
        splashColor: Colors.transparent,
        highlightColor: Colors.transparent,
      ),
      home: MyHomePage(title: 'android-util'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        centerTitle: false,
      ),
      body: new ListView(children: _initListData()),
    );
  }

  _initListData() {
    List<Widget> list = [];
    list.add(_customButton("启动Flutter的界面", 1));
    return list;
  }

  _customButton(String title, int type) {
    return new Padding(
        padding: (type == 1
            ? new EdgeInsets.only(
            left: 20.0, top: 25.0, right: 20.0, bottom: 5.0)
            : new EdgeInsets.symmetric(vertical: (5), horizontal: 20)),
        child: FlatButton(
          color: Colors.blue,
          highlightColor: Colors.blue[700],
          colorBrightness: Brightness.dark,
          splashColor: Colors.grey,
          child: Text(title),
          shape:
          RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.0)),
          onPressed: () => _onClick(type),
        ));
  }

  _onClick(int type) {
    
  }
}
